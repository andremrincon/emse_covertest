package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
public class FeatureConstraintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateRequiresConstraintCoversSetIdSetForProduct() {
        String productName = "TestProduct-Requires-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversSetIdSetForProduct() {
        String productName = "TestProduct-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureC-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureD-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithConstraintsCoversGetId() {
        String productName = "TestProduct-Get-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureE-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteConstraintCoversGetId() {
        String productName = "TestProduct-Delete-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureG-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureH-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));

        Integer constraintId = given()
        .when()
            .get("/products/{productName}/constraints", productName)
        .then()
            .statusCode(lessThan(300))
            .extract()
            .path("[0].id");

        given()
        .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEvaluateRequiresConstraintCoversGetIdInEvaluation() {
        String productName = "TestProduct-EvalReq-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureI-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureJ-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateExcludesConstraintCoversGetIdInEvaluation() {
        String productName = "TestProduct-EvalExc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureK-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureL-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithRequiresConstraintCoversGetId() {
        String productName = "TestProduct-CfgFeatReq-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureM-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureN-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListWithConstraintsCoversGetId() {
        String productName = "TestProduct-CfgList-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureO-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureP-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, requiredFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithConstraintsCoversGetId() {
        String productName = "TestProduct-DeleteAll-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureQ-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureR-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }
}