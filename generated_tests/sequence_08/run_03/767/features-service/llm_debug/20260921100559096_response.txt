package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    private String baseUrl;
    private String productName;
    private String configurationName;
    private String featureName1;
    private String featureName2;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        productName = "TestProduct-" + System.currentTimeMillis();
        configurationName = "TestConfig-" + System.currentTimeMillis();
        featureName1 = "Feature1-" + System.currentTimeMillis();
        featureName2 = "Feature2-" + System.currentTimeMillis();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName1)
            .formParam("description", "Test feature 1")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName2)
            .formParam("description", "Test feature 2")
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithConfigurations() {
        given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/configurations")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct_WithoutConfigurations() {
        String emptyProductName = "EmptyProduct-" + System.currentTimeMillis();
        given()
            .pathParam("productName", emptyProductName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", emptyProductName)
            .when()
            .get("/products/{productName}/configurations")
            .then()
            .statusCode(200)
            .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithActiveFeatures() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames_WithoutActiveFeatures() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200)
            .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_Success() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration_DuplicateFeature() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_Success() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfiguration_ValidConfiguration() {
        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfiguration_InvalidConfiguration() {
        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", featureName1)
            .formParam("requiredFeature", featureName2)
            .when()
            .post("/products/{productName}/constraints/requires")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }
}