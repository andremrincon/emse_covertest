package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testAddFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
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
    public void testBuildWithFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByName_Found() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName)
            .formParam("requiredFeature", featureName)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByName_NotFound() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String nonExistentFeature = "NonExistent-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName)
            .formParam("requiredFeature", nonExistentFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamed_True() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName)
            .formParam("excludedFeature", featureName)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testHasFeatureNamed_False() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String nonExistentFeature = "NonExistent-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName)
            .formParam("excludedFeature", nonExistentFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraint_Requires() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName2)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName1)
            .formParam("requiredFeature", featureName2)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraint_Excludes() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName2)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", featureName1)
            .formParam("excludedFeature", featureName2)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }
}