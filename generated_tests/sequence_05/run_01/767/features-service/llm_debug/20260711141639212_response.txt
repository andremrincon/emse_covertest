package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintCoversConstructorAndType() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testEvaluateConfigurationBothFeaturesActiveInvalid() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature)
                .then()
                .statusCode(500);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200)
                .body("valid", is(false));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlySourceFeatureActiveValid() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, sourceFeature).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationOnlyExcludedFeatureActiveValid() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationNeitherFeatureActiveValid() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesAfterExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
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
    public void testExcludesConstraintWithNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintMissingSourceFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintMissingExcludedFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));

        String location = given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201)
                .extract()
                .header("Location");

        if (location != null && !location.isEmpty()) {
            String constraintId = location.substring(location.lastIndexOf('/') + 1);
            given()
                    .when()
                    .delete("/products/{productName}/constraints/{constraintId}", productName, constraintId)
                    .then()
                    .statusCode(204);
        }
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationAfterRemovingExcludedFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
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
    public void testGetConfigurationValidAfterRemovingSourceFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
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

    @Test(timeout = 60000)
    public void testMultipleExcludesConstraintsOnSameProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature1 = "SrcF1-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature1 = "ExcF1-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature2 = "SrcF2-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature2 = "ExcF2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature2).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature1)
                .formParam("excludedFeature", excludedFeature1)
        .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature2)
                .formParam("excludedFeature", excludedFeature2)
        .when()
                .post("/products/{productName}/constraints/excludes", productName)
        .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintResponseContainsType() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .statusCode(201)
                .body(equalTo(""));
    }
}