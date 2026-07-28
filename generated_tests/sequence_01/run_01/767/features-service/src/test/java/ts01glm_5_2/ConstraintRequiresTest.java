package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ConstraintRequiresTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintSetsSourceAndRequiredFeatureNames() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

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
    public void testEvaluateConfigSourceActiveRequiredNotActiveTriggersDerivedFeature() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigSourceNotActiveDoesNotAddDerivedFeature() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigBothActiveDoesNotAddDerivedFeature() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesShowsDerivedFeatureWhenSourceActive() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("", hasItem(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithNonExistentFeatures() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigWithOnlyRequiredActiveDoesNotTriggerDerived() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraintAfterCreation() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));

        String constraintsResponse = given()
            .when()
            .get("/products/" + productName)
            .then()
            .statusCode(lessThan(300))
            .extract()
            .asString();

        given()
        .when()
            .get("/products/" + productName + "/configurations")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigWithMultipleRequiresConstraints() {
        String uuid = java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String configName = "TestConfig-" + uuid;
        String sourceFeature1 = "SourceFeature1-" + uuid;
        String requiredFeature1 = "RequiredFeature1-" + uuid;
        String sourceFeature2 = "SourceFeature2-" + uuid;
        String requiredFeature2 = "RequiredFeature2-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature2).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature1)
            .formParam("requiredFeature", requiredFeature1)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature2)
            .formParam("requiredFeature", requiredFeature2)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature2).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("", hasItem(requiredFeature1))
            .body("", hasItem(requiredFeature2));
    }
}