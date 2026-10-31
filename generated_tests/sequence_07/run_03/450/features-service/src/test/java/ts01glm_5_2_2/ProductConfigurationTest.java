package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @Before
    public void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationWithProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeaturesWithProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByNameWithProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID();
        String configurationName = "TestConfig-" + UUID.randomUUID();
        String featureName = "TestFeature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(200);
    }
}