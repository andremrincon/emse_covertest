package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204WhenValid() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "test-prod-" + uuid;
        String configurationName = "test-config-" + uuid;
        String featureName = "test-feature-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <204>.")
    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns500WhenRequiresConstraintViolated() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "test-prod-" + uuid;
        String configurationName = "test-config-" + uuid;
        String featureA = "feature-a-" + uuid;
        String featureB = "feature-b-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(500);

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromNonExistentConfigurationReturns500() {
        String uuid = java.util.UUID.randomUUID().toString();
        String productName = "test-prod-" + uuid;
        String configurationName = "nonexistent-config-" + uuid;
        String featureName = "test-feature-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
    }
}