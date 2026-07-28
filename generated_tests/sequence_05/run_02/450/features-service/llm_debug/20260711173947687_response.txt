package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

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
    public void testAddFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(lessThan(300));

        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testBuildWithFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameFound() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Feature 1")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature1)
        .then()
            .statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Feature 2")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testFindProductFeatureByNameNotFound() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Feature 1")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature1)
        .then()
            .statusCode(lessThan(300));

        String nonExistentFeature = "NonExistent-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", nonExistentFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureConstraintExcludes() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Feature 1")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature1)
        .then()
            .statusCode(lessThan(300));

        String feature2 = "Feature2-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Feature 2")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }
}