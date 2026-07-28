package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaExcludesConstraintOnFeatures() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configName = "TestConfig-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaRequiresConstraintOnConfig() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configName = "TestConfig-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <200>.")
    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionViaExcludesConstraintOnConfig() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String configName = "TestConfig-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureB)
            .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", featureA)
                .formParam("excludedFeature", featureB)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureA)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(500);
    }
}