package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintCallsSetters() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testEvaluateConfigurationBothFeaturesActiveSetsInvalid() {
        String productName = "EvalProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActiveRemainsValid() {
        String productName = "ValidProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "VSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "VExc-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "VConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActiveRemainsValid() {
        String productName = "ValidProduct2-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "VS2-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "VE2-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "VC2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNoFeaturesActiveRemainsValid() {
        String productName = "EmptyProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "ES-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "EE-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "EC-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesTriggersEvaluationBothActive() {
        String productName = "FeatEvalProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FES-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FEE-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "FEC-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithDifferentFeatureNames() {
        String productName = "DiffProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "DiffSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "DiffExc-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testEvaluateConfigurationAfterRemovingExcludedFeatureBecomesValid() {
        String productName = "RemoveProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "RExc-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "RConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAfterRemovingSourceFeatureBecomesValid() {
        String productName = "RemoveSrcProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RmSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "RmExc-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "RmConfig-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
                .statusCode(200)
                .body("valid", is(true));
    }
}