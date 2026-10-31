package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

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
    public void testAddFeatureToProductDuplicate() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductWithDescription() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature description")
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithActiveConfiguration() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();

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
                .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWithoutConfiguration() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

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
    public void testAddRequiresConstraintToProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String requiredFeature = "req-feat-" + UUID.randomUUID();

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
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String excludedFeature = "exc-feat-" + UUID.randomUUID();

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
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithoutExistingFeatures() {
        String productName = "test-prod-" + UUID.randomUUID();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "FeatureA")
            .formParam("requiredFeature", "FeatureB")
            .when()
                .post("/products/{productName}/constraints/requires", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithoutExistingFeatures() {
        String productName = "test-prod-" + UUID.randomUUID();

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", "FeatureA")
            .formParam("excludedFeature", "FeatureB")
            .when()
                .post("/products/{productName}/constraints/excludes", productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductNotFound() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "nonexistent-feat";

        given()
            .when()
                .post("/products/{productName}", productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductOnNonExistentProduct() {
        String productName = "nonexistent-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

        given()
            .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
                .statusCode(500);
    }
}