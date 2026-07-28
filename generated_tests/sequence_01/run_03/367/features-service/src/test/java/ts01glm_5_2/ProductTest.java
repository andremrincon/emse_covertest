package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaProductCreation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200)
                .body("name", equalTo(productName));
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintRequires() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

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
    public void testAddFeatureConstraintExcludes() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then()
                .statusCode(lessThan(300));

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
    public void testAddFeatureWithDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);
        String description = "A test feature description";

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", description)
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureThenVerifyRemoved() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeaturesAndConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200)
                .body("name", equalTo(productName));
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/features", productName)
                .then()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintRequiresWithExistingFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "SrcFeat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "ReqFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}", productName)
                .then()
                .statusCode(200)
                .body("constraints", notNullValue());
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithFeaturesAndConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}", productName)
                .then()
                .statusCode(204);
    }
}