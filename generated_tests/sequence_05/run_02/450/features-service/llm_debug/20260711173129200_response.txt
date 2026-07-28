package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setUpClass() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetTypeViaGetProduct() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName)
        .then()
                .statusCode(200)
                .body("constraints", notNullValue());
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceActiveRequiredNotActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyRequiredActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationDetailsWithRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingSourceFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingRequiredFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleRequiresConstraints() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature1 = "src1-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature1 = "req1-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature2 = "src2-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature2 = "req2-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature2).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature1)
                .formParam("requiredFeature", requiredFeature1)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature2)
                .formParam("requiredFeature", requiredFeature2)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature2).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName)
        .then()
                .statusCode(200)
                .body("constraints", notNullValue());
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAfterRemovingSourceFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListWithRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/" + productName + "/configurations")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProduct() {
        String productName = "nonexistent-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
        .when()
                .post("/products/" + productName + "/constraints/requires")
        .then()
                .statusCode(500);
    }
}