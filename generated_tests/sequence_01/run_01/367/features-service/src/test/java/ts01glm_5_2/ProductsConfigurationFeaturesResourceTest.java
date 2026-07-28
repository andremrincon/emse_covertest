package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsNoContentWhenFeatureExists() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString();
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsErrorWhenFeatureNotInConfiguration() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + java.util.UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsErrorWhenProductDoesNotExist() {
        String productName = "NonExistentProduct-" + java.util.UUID.randomUUID().toString();
        String configurationName = "NonExistentConfig-" + java.util.UUID.randomUUID().toString();
        String featureName = "NonExistentFeature-" + java.util.UUID.randomUUID().toString();

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(500);
    }
}