package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void excludesConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "test-excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "featA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "featB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void requiresConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "test-requires-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "reqA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "reqB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "test-getconfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "gA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "gB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA).then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
        .then()
            .statusCode(500);
    }
}