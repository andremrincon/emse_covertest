package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiationViaRequiresConstraintEvaluation() {
        String productName = "EvalResult-Requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";
        String configurationName = "base-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
            .then()
                .statusCode(lessThan(300));

        Map<String, String> requiresParams = new HashMap<>();
        requiresParams.put("sourceFeature", sourceFeature);
        requiresParams.put("requiredFeature", requiredFeature);

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParams(requiresParams)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiationViaExcludesConstraintEvaluation() {
        String productName = "EvalResult-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";
        String configurationName = "std-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
            .then()
                .statusCode(lessThan(300));

        Map<String, String> excludesParams = new HashMap<>();
        excludesParams.put("sourceFeature", sourceFeature);
        excludesParams.put("excludedFeature", excludedFeature);

        RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParams(excludesParams)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
            .then()
                .statusCode(500);

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiationViaConfigurationRetrieval() {
        String productName = "EvalResult-Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "prod-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "SSD-Storage";

        RestAssured.given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(lessThan(300));

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }
}