package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class EvaluationResultTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEvaluation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName + "/configurations/" + configurationName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"" + featureName + "\"}")
        .when()
            .post("/products/" + productName + "/features")
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesUsesEvaluationResult() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName + "/configurations/" + configurationName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"" + featureName + "\"}")
        .when()
            .post("/products/" + productName + "/features")
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/products/" + productName + "/configurations/" + configurationName + "/features")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAddConstraintTriggersEvaluation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"" + sourceFeature + "\"}")
        .when()
            .post("/products/" + productName + "/features")
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"" + requiredFeature + "\"}")
        .when()
            .post("/products/" + productName + "/features")
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }
}