package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    private String createProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        return productName;
    }

    private void createFeature(String productName, String featureName) {
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
    }

    private void createExcludesConstraint(String productName, String sourceFeature, String excludedFeature) {
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(lessThan(300));
    }

    private void createConfiguration(String productName, String configName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configName, String featureName) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configName, String featureName, int expectedStatus) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(expectedStatus);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint() {
        String productName = createProduct();
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();
        createFeature(productName, featureA);
        createFeature(productName, featureB);

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWithExcludesConstraint() {
        String productName = createProduct();
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();
        createFeature(productName, featureA);
        createFeature(productName, featureB);
        createExcludesConstraint(productName, featureA, featureB);

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationValid() {
        String productName = createProduct();
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();
        createFeature(productName, featureA);
        createFeature(productName, featureB);
        createExcludesConstraint(productName, featureA, featureB);

        String configName = "Config-" + UUID.randomUUID().toString();
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureA);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateConfigurationInvalid() {
        String productName = createProduct();
        String featureA = "FeatureA-" + UUID.randomUUID().toString();
        String featureB = "FeatureB-" + UUID.randomUUID().toString();
        createFeature(productName, featureA);
        createFeature(productName, featureB);
        createExcludesConstraint(productName, featureA, featureB);

        String configName = "Config-" + UUID.randomUUID().toString();
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureA);
        addFeatureToConfiguration(productName, configName, featureB, 500);

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .body("valid", equalTo(false));
    }
}