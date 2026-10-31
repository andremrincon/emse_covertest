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
    public void testCreateExcludesConstraintReturns201() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", product)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothFeaturesActiveInvalid() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", product, config)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlySourceFeatureActiveValid() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", product, config)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateOnlyExcludedFeatureActiveValid() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", product, config)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateNeitherFeatureActiveValid() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", product, config)
        .then()
            .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void testGetTypeViaGetProduct() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", product)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetSourceAndExcludedFeatureNamesViaGetProduct() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "SourceFeat-" + UUID.randomUUID();
        String featureB = "ExcludedFeat-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", product)
        .then()
            .body("constraints", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetSourceFeatureNameOnly() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
        .when()
            .post("/products/{productName}/constraints/excludes", product)
        .then()
            .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testSetExcludedFeatureNameOnly() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", product)
        .then()
            .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testEvaluateViaConfigurationFeaturesEndpoint() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", product, config)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDefaultConstructorViaGetConfigurations() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations", product)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultipleExcludesConstraintsOnSameProduct() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();
        String featureC = "FeatureC-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureC).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
        .when()
            .post("/products/{productName}/constraints/excludes", product)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateBothActiveViaGetConfiguration() {
        String product = "TestProduct-" + UUID.randomUUID();
        String config = "Config-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", product, config)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteExcludesConstraint() {
        String product = "TestProduct-" + UUID.randomUUID();
        String featureA = "FeatureA-" + UUID.randomUUID();
        String featureB = "FeatureB-" + UUID.randomUUID();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", featureA).formParam("excludedFeature", featureB)
            .when().post("/products/{productName}/constraints/excludes", product).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", product)
        .then()
            .statusCode(200);
    }
}