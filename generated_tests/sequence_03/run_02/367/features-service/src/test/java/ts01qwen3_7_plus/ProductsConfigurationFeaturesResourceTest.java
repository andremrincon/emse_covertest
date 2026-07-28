package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureSuccessfully() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureThatDoesNotExist() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(500);
    }
}