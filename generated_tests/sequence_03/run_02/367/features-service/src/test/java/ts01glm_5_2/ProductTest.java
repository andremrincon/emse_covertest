package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description")
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
    public void testAddRequiresConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + requiredFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + sourceFeature)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testGetFeaturesForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
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
    public void testGetAllProducts() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testDeleteProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "cfg-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200);
    }
}