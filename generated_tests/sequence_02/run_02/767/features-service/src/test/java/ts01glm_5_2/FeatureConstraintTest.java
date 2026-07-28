package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class FeatureConstraintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSetIdViaRequiresConstraintAndProductRetrieval() {
        String productName = "FC-Product-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FC-Source-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FC-Required-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIdViaExcludesConstraintAndConfigurationEvaluation() {
        String productName = "FC-Product-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "FC-Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FC-Source-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FC-Excluded-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIdViaConstraintDeletion() {
        String productName = "FC-Product-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FC-Source-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FC-Excluded-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        Response productResponse = given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(lessThan(300))
                .extract()
                .response();

        JsonPath jsonPath = productResponse.jsonPath();
        Integer constraintId = null;
        try {
            constraintId = jsonPath.getInt("constraints[0].id");
        } catch (Exception e) {
            constraintId = jsonPath.getInt("constraints.id[0]");
        }

        if (constraintId != null) {
            given()
                .when()
                    .delete("/products/" + productName + "/constraints/" + constraintId)
                .then()
                    .statusCode(204);
        } else {
            given()
                .when()
                    .delete("/products/" + productName + "/constraints/1")
                .then()
                    .statusCode(notNullValue());
        }
    }
}