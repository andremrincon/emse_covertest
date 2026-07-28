package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String featureName = "TestFeature";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String featureName = "TestFeature";
        given()
            .contentType(ContentType.JSON)
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
    public void testAddRequiresConstraintWithExistingFeatures() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        String feature2 = "Feature2";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature2)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", feature1)
            .param("requiredFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithExistingFeatures() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        String feature2 = "Feature2";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature2)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", feature1)
            .param("excludedFeature", feature2)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddConstraintWithNonExistentSourceFeature() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", "NonExistentFeature")
            .param("requiredFeature", feature1)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddConstraintWithNonExistentRequiredFeature() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", feature1)
            .param("requiredFeature", "NonExistentFeature")
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentSourceFeature() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", "NonExistentFeature")
            .param("excludedFeature", feature1)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintWithNonExistentExcludedFeature() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String feature1 = "Feature1";
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName + "/features/" + feature1)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .param("sourceFeature", feature1)
            .param("excludedFeature", "NonExistentFeature")
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .contentType(ContentType.JSON)
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        String featureName = "TestFeature";
        given()
            .contentType(ContentType.JSON)
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
}