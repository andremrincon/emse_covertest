package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesTriggersEvaluationResultConstruction() {
        String productName = "eval-test-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "eval-test-feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void getActiveFeaturesWithRequiresConstraintTriggersEvaluationResult() {
        String productName = "eval-req-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "eval-req-source-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "eval-req-required-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-req-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature)
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
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getActiveFeaturesWithExcludesConstraintTriggersEvaluationResult() {
        String productName = "eval-excl-product-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "eval-excl-source-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "eval-excl-excluded-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "eval-excl-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200);
    }
}