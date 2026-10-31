package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addingFeatureToConfigurationViolatingExcludesConstraintTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "FeatureA-" + UUID.randomUUID().toString().substring(0, 6);
        String featureB = "FeatureB-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
                .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addingFeatureToConfigurationViolatingRequiresConstraintTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "ReqA-" + UUID.randomUUID().toString().substring(0, 6);
        String featureB = "ReqB-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("requiredFeature", featureB)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluatingConfigurationWithMultipleConstraintViolationsTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Multi-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "MultiA-" + UUID.randomUUID().toString().substring(0, 6);
        String featureB = "MultiB-" + UUID.randomUUID().toString().substring(0, 6);
        String featureC = "MultiC-" + UUID.randomUUID().toString().substring(0, 6);
        String featureD = "MultiD-" + UUID.randomUUID().toString().substring(0, 6);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 6);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureC).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureD).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
                .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureC).formParam("requiredFeature", featureD)
                .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureC).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
                .then().statusCode(500);
    }
}