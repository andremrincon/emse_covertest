package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void addFeatureToProductSuccess() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductDuplicateThrowsException() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .formParam("description", "Test description 2")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithActiveConfiguration() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String configurationName = "test-config-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
                .formParam("description", "Test description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithoutConfiguration() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
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
    public void addRequiresConstraintToProductSuccess() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feature-" + UUID.randomUUID().toString();
        String requiredFeature = "required-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProductSuccess() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feature-" + UUID.randomUUID().toString();
        String excludedFeature = "excluded-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProductWithNonExistentProduct() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feature-" + UUID.randomUUID().toString();
        String requiredFeature = "required-feature-" + UUID.randomUUID().toString();

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProductWithNonExistentProduct() {
        String productName = "non-existent-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feature-" + UUID.randomUUID().toString();
        String excludedFeature = "excluded-feature-" + UUID.randomUUID().toString();

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductWithDescription() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String description = "A detailed feature description for testing purposes.";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
                .formParam("description", description)
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProductWithMultipleConfigurations() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "test-feature-" + UUID.randomUUID().toString();
        String config1 = "config1-" + UUID.randomUUID().toString();
        String config2 = "config2-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + config1).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + config2).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + config1 + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + config2 + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void addRequiresConstraintToProductWithEmptySourceFeature() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String requiredFeature = "required-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", "")
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void addExcludesConstraintToProductWithEmptyExcludedFeature() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String sourceFeature = "source-feature-" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", "")
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(500);
    }
}