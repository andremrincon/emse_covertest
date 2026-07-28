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
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature-" + UUID.randomUUID().toString()).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
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
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "exc-" + UUID.randomUUID().toString();
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
    public void testGetProductFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        given().when().get("/products").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }
}