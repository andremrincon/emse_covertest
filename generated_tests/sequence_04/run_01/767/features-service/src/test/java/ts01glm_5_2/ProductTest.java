package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description")
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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
    public void testGetProductFeaturesAfterAdd() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/features", productName)
            .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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
    public void testAddExcludesConstraint() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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
    public void testBuildWithFeaturesViaConfigurationEvaluation() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameWithFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}", productName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
                .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithFeaturesAndConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameForProduct() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();
        String configurationName = "TestConfig-" + UUID.randomUUID().toString();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromNonExistentProduct() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString();
        String featureName = "TestFeature-" + UUID.randomUUID().toString();

        given()
            .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(500);
    }
}