package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    private String createProduct() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));
        return productName;
    }

    private void addFeature(String productName, String featureName, String description) {
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", description)
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void addFeatureToProductSuccess() {
        String productName = createProduct();
        String featureName = "feature-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductDuplicateThrowsException() {
        String productName = createProduct();
        String featureName = "feature-" + UUID.randomUUID().toString();

        addFeature(productName, featureName, "First description");

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Second description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithActiveConfiguration() {
        String productName = createProduct();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configName = "config-" + UUID.randomUUID().toString();

        addFeature(productName, featureName, "Test description");

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
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithoutConfiguration() {
        String productName = createProduct();
        String featureName = "feature-" + UUID.randomUUID().toString();

        addFeature(productName, featureName, "Test description");

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProductSuccess() {
        String productName = createProduct();
        String sourceFeature = "source-" + UUID.randomUUID().toString();
        String requiredFeature = "required-" + UUID.randomUUID().toString();

        addFeature(productName, sourceFeature, "Source feature");
        addFeature(productName, requiredFeature, "Required feature");

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
    public void addRequiresConstraintToProductNonExistentProduct() {
        String productName = "nonexistent-" + UUID.randomUUID().toString();
        String sourceFeature = "source-" + UUID.randomUUID().toString();
        String requiredFeature = "required-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProductSuccess() {
        String productName = createProduct();
        String sourceFeature = "source-" + UUID.randomUUID().toString();
        String excludedFeature = "excluded-" + UUID.randomUUID().toString();

        addFeature(productName, sourceFeature, "Source feature");
        addFeature(productName, excludedFeature, "Excluded feature");

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
    public void addExcludesConstraintToProductNonExistentProduct() {
        String productName = "nonexistent-" + UUID.randomUUID().toString();
        String sourceFeature = "source-" + UUID.randomUUID().toString();
        String excludedFeature = "excluded-" + UUID.randomUUID().toString();

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(500);
    }
}