package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceFeatureActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlyExcludedFeatureActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateNeitherFeatureActive() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
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
    public void testGetProductWithExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllConfigurationsWithExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActiveBodyCheck() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceFeatureActiveBodyCheck() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();
        String configName = "Cfg-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }
}