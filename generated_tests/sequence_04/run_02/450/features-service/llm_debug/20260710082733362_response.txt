package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class ConfigurationEvaluatorTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithRequiresConstraintDerivesFeatureTest() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalReq-Prod-" + uuid;
        String sourceFeature = "EvalReq-Src-" + uuid;
        String requiredFeature = "EvalReq-Req-" + uuid;
        String configName = "EvalReq-Cfg-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithExcludesConstraintViolationTest() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalExc-Prod-" + uuid;
        String sourceFeature = "EvalExc-Src-" + uuid;
        String excludedFeature = "EvalExc-Exc-" + uuid;
        String configName = "EvalExc-Cfg-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void evaluateConfigurationWithNoConstraintsTest() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "EvalNone-Prod-" + uuid;
        String featureName = "EvalNone-Feat-" + uuid;
        String configName = "EvalNone-Cfg-" + uuid;

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(200);
    }
}