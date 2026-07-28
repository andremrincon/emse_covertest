package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateProduct() {
        String productName = "test-product-" + UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "test-product-" + UUID.randomUUID();
        String sourceFeature = "source-feature-" + UUID.randomUUID();
        String requiredFeature = "required-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "test-product-" + UUID.randomUUID();
        String sourceFeature = "source-feature-" + UUID.randomUUID();
        String excludedFeature = "excluded-feature-" + UUID.randomUUID();

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
    public void testGetProductWithFeatures() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature with description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductAfterFeatureOperations() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}", productName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String feature1 = "feature-one-" + UUID.randomUUID();
        String feature2 = "feature-two-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToNonExistentProduct() {
        String productName = "nonexistent-product-" + UUID.randomUUID();

        given()
            .formParam("sourceFeature", "featureA")
            .formParam("requiredFeature", "featureB")
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description for feature")
        .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }
}