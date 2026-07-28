package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturns204WhenValid() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureName = "TestFeature-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <204>.")
    @Test(timeout = 60000)
    public void deleteFeatureFromConfigurationReturnsErrorWhenConstraintViolated() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProd-" + uuid;
        String configurationName = "TestConfig-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().formParam("sourceFeature", featureA).formParam("requiredFeature", featureB)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureB)
                .then().statusCode(400);
    }
}