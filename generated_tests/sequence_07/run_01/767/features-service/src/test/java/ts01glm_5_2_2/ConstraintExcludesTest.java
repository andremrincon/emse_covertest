package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Excl-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        String productName = "ExclGet-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200)
            .body("constraints", notNullValue());
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActive() {
        String productName = "EvalBoth-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcA-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcA-" + UUID.randomUUID().toString().substring(0, 4);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceActive() {
        String productName = "EvalSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcB-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcB-" + UUID.randomUUID().toString().substring(0, 4);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlyExcludedActive() {
        String productName = "EvalExc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcC-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcC-" + UUID.randomUUID().toString().substring(0, 4);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateNeitherFeatureActive() {
        String productName = "EvalNone-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcD-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcD-" + UUID.randomUUID().toString().substring(0, 4);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200)
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintMissingSourceFeature() {
        String productName = "ExclNoSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcE-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintMissingExcludedFeature() {
        String productName = "ExclNoExc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcE-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintOnNonExistentProduct() {
        String productName = "NonExistent-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcX-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcX-" + UUID.randomUUID().toString().substring(0, 4);

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithNonExistentFeatures() {
        String productName = "ExclNoFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "NonExistentSrc-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "NonExistentExc-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String productName = "ExclDel-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcG-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcG-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        Integer constraintId = given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200)
            .extract()
            .path("constraints[0].id");

        given()
        .when()
            .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigFeaturesWithExcludesConstraintBothActive() {
        String productName = "ExclCfgFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcH-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcH-" + UUID.randomUUID().toString().substring(0, 4);
        String configName = "Cfg-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateDuplicateExcludesConstraint() {
        String productName = "ExclDup-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcI-" + UUID.randomUUID().toString().substring(0, 4);
        String excludedFeature = "ExcI-" + UUID.randomUUID().toString().substring(0, 4);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));
    }
}