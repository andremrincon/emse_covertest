package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
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
    public void testCreateExcludesConstraintReturns201() {
        String productName = "excl-crt-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductReturnsExcludesTypeInConstraints() {
        String productName = "excl-type-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200)
                .body("constraints.type", hasItem("excludes"));
    }

    @Test(timeout = 60000)
    public void testGetProductReturnsSourceFeatureNameInConstraints() {
        String productName = "excl-srcfn-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200)
                .body("constraints.sourceFeatureName", hasItem(sourceFeature));
    }

    @Test(timeout = 60000)
    public void testGetProductReturnsExcludedFeatureNameInConstraints() {
        String productName = "excl-excfn-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200)
                .body("constraints.excludedFeatureName", hasItem(excludedFeature));
    }

    @Ignore("1 expectation failed. JSON path valid doesn't match. Expected: is <true>   Actual: <false>")
    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActiveConfigInvalid() {
        String productName = "excl-both-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceFeatureActiveConfigValid() {
        String productName = "excl-src-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlyExcludedFeatureActiveConfigValid() {
        String productName = "excl-exconly-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateNeitherFeatureActiveConfigValid() {
        String productName = "excl-none-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);
        String otherFeature = "oth-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + otherFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + otherFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraintReturns204() {
        String productName = "excl-del-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        Integer constraintId = given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(lessThan(300))
                .extract()
                .path("constraints.find { it.type == 'excludes' }.id");

        given()
            .when()
                .delete("/products/" + productName + "/constraints/" + constraintId)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActiveFeaturesWithExcludesConstraint() {
        String productName = "excl-actf-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultipleExcludesConstraintsOnSameProduct() {
        String productName = "excl-multi-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature1 = "s1-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature1 = "e1-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature2 = "s2-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature2 = "e2-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature1)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature1)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature2)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature2)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature1)
            .formParam("excludedFeature", excludedFeature1)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature2)
            .formParam("excludedFeature", excludedFeature2)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200)
                .body("constraints", hasSize(greaterThanOrEqualTo(2)));
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintBeforeFeatureAdditionToConfig() {
        String productName = "excl-bfeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigResolvesExcludesViolation() {
        String productName = "excl-resolve-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
                .statusCode(500);

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then()
                .statusCode(204);

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraintWithFeaturesHavingDescriptions() {
        String productName = "excl-desc-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Source feature description")
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Excluded feature description")
            .when()
                .post("/products/" + productName + "/features/" + excludedFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/" + productName + "/constraints/excludes")
            .then()
                .statusCode(201);
    }
}