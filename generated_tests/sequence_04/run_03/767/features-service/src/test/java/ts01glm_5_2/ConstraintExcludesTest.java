package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversGetTypeAndSetters() {
        String productName = "TestProduct-Excludes-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testEvaluateConfigurationBothFeaturesActiveInvalid() {
        String productName = "TestProduct-BothActive-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActiveValid() {
        String productName = "TestProduct-OnlySrc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActiveValid() {
        String productName = "TestProduct-OnlyExc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveValid() {
        String productName = "TestProduct-Neither-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String otherFeature = "OtherF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, otherFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, otherFeature)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithExcludesConstraintCoversGetters() {
        String productName = "TestProduct-GetConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesWithExcludesConstraint() {
        String productName = "TestProduct-GetFeatures-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraintCoversGetType() {
        String productName = "TestProduct-GetProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
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
    public void testDeleteExcludesConstraintAfterCreation() {
        String productName = "TestProduct-DelConstraint-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        String constraintId = given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300))
                .extract().path("constraints[0].id").toString();

        given()
                .when()
                .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
                .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithBothFeaturesActiveReturnsInvalidConfig() {
        String productName = "TestProduct-InvalidConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithOnlySourceActiveReturnsValidConfig() {
        String productName = "TestProduct-ValidConfigSrc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithOnlyExcludedActiveReturnsValidConfig() {
        String productName = "TestProduct-ValidConfigExc-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithNeitherActiveReturnsValidConfig() {
        String productName = "TestProduct-ValidConfigNeither-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListWithExcludesConstraint() {
        String productName = "TestProduct-ConfigList-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigWithExcludesConstraint() {
        String productName = "TestProduct-DelFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature)
                .then()
                .statusCode(204);
    }
}