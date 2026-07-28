package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationDetails_callsAvailableFeatures_returns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_callsAvailableFeatures_returns201() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_callsAvailableFeatures_returns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when()
                .get("/products/{productName}/configurations/{configurationName}/features",
                        productName, configurationName)
                .then().statusCode(200);
    }
}