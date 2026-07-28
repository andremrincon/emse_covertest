package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
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
    public void testEvaluateConfigurationSourceActiveRequiredNotActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
    public void testEvaluateConfigurationBothFeaturesActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("", containsInAnyOrder(sourceFeature, requiredFeature));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationSourceNotActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyRequiredActive() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
            .statusCode(200)
            .body("", contains(requiredFeature));
    }

    @Test(timeout = 60000)
    public void testGetProductWithRequiresConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
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

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingSourceFeature() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithMissingRequiredFeature() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintOnNonExistentProduct() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "NonExistentProduct-" + uuid;

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleRequiresConstraints() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String featureA = "FeatureA-" + uuid;
        String featureB = "FeatureB-" + uuid;
        String featureC = "FeatureC-" + uuid;
        String configName = "Config-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureC).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureB)
            .formParam("requiredFeature", featureC)
        .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200)
            .body("", containsInAnyOrder(featureA, featureB, featureC));
    }

    @Test(timeout = 60000)
    public void testDeleteRequiresConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
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

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationEmptyConfigWithRequiresConstraint() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
    public void testCreateRequiresConstraintWithEmptySourceFeature() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", "")
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintWithEmptyRequiredFeature() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", "")
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAfterRemovingSourceFeature() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "TestProduct-" + uuid;
        String sourceFeature = "SourceFeature-" + uuid;
        String requiredFeature = "RequiredFeature-" + uuid;
        String configName = "Config-" + uuid;

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
        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }
}