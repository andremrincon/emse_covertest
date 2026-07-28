package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testCreateProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productName + "/features")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.URLENC)
                .formParam("description", "Updated description")
                .when()
                .put("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
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
    public void testAddExcludesConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
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
    public void testAddFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productName + "/configurations")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductByName() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + productName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.URLENC)
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productName + "/configurations/" + configurationName + "/features")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(204);
    }
}