package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
        String productName = "Prod-" + UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeaturesViaConfigurationEvaluation() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();
        String configName = "Config-" + UUID.randomUUID();

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
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String requiredFeature = "Req-" + UUID.randomUUID();

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
    public void testAddExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID();
        String sourceFeature = "Src-" + UUID.randomUUID();
        String excludedFeature = "Exc-" + UUID.randomUUID();

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
    public void testGetProductFeaturesAfterAdd() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
    public void testGetProductByName() {
        String productName = "Prod-" + UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description for feature")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct() {
        String productName = "Prod-" + UUID.randomUUID();
        String featureName = "Feat-" + UUID.randomUUID();

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
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = "Prod-" + UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}", productName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given()
            .when()
            .get("/products")
            .then()
            .statusCode(200);
    }
}