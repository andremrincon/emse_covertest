package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class EvaluationResultTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testEvaluationResultCreationOnGetConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluationResultCreationOnAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluationResultCreationWithConstraints() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        String featureA = "FeatA-" + UUID.randomUUID().toString();
        String featureB = "FeatB-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Feature A")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureA)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Feature B")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureB)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
            .post("/products/{productName}/constraints/requires", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(200);
    }
}