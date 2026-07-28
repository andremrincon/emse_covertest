package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/TestFeature-" + UUID.randomUUID().toString().substring(0, 8))
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaProductCreation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetAllProducts() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products")
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
                .post("/products/" + productName + "/features/" + requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
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
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "A test feature description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductAfterAdd() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/features")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureThenVerifyRemoved() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/features")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaGetProductByName() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/FeatureA-" + UUID.randomUUID().toString().substring(0, 8))
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/FeatureB-" + UUID.randomUUID().toString().substring(0, 8))
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200)
                .body("features", notNullValue());
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintRequiresWithExistingProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "RAID-Controller-Card";
        String requiredFeature = "128GB-ECC-RAM";

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
                .post("/products/" + productName + "/features/" + requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintExcludesWithExistingProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "CPU-i9-13900H";
        String excludedFeature = "Integrated-Graphics-Only";

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
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }
}