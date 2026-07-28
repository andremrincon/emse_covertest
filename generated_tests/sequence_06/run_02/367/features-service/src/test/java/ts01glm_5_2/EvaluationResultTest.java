package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesTriggersEvaluationResultConstruction() {
        String productName = "eval-prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();

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
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithRequiresConstraintTriggersEvaluationResult() {
        String productName = "eval-prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();

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
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithExcludesConstraintTriggersEvaluationResult() {
        String productName = "eval-prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "exc-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();

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
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }
}