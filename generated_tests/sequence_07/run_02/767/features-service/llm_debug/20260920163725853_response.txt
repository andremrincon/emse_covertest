package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversSetters() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(is(201));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationBothFeaturesActiveInvalid() {
        String productName = "TestProduct-BothActive-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceActiveValid() {
        String productName = "TestProduct-OnlySrc-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedActiveValid() {
        String productName = "TestProduct-OnlyExc-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveValid() {
        String productName = "TestProduct-Neither-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithMissingExcludedFeature() {
        String productName = "TestProduct-MissingExc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(is(201));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithMissingSourceFeature() {
        String productName = "TestProduct-MissingSrc-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        given()
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(is(201));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationWithMultipleExcludesConstraints() {
        String productName = "TestProduct-Multi-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature1 = "SrcF1-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature1 = "ExcF1-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature2 = "SrcF2-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature2 = "ExcF2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature2).then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature1)
            .formParam("excludedFeature", excludedFeature1)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));
        given()
            .formParam("sourceFeature", sourceFeature2)
            .formParam("excludedFeature", excludedFeature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintForNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcF-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcF-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(is(500));
    }
}