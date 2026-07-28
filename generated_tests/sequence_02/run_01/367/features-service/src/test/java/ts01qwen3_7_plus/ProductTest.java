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
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/features")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("requiredFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", feature1)
                .formParam("excludedFeature", feature2)
                .when()
                .post("/products/" + productName + "/constraints/excludes")
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("description", "Updated description")
                .when()
                .put("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/features/" + feature2)
                .then()
                .statusCode(201);
    }
}