package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        String description = "Test feature description";

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("description", description)
        .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        io.restassured.response.Response response = given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_WithActiveConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        io.restassured.response.Response response = given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "source")
        .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
        .then().statusCode(lessThan(300));

        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "required")
        .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
        .then().statusCode(lessThan(300));

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_NonExistentProduct() {
        String productName = "NonExistent-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String requiredFeature = "RequiredFeature-" + UUID.randomUUID().toString();

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires");

        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "source")
        .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
        .then().statusCode(lessThan(300));

        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "excluded")
        .when()
            .post("/products/" + productName + "/features/" + excludedFeature)
        .then().statusCode(lessThan(300));

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_NonExistentProduct() {
        String productName = "NonExistent-" + UUID.randomUUID().toString();
        String sourceFeature = "SourceFeature-" + UUID.randomUUID().toString();
        String excludedFeature = "ExcludedFeature-" + UUID.randomUUID().toString();

        io.restassured.response.Response response = given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes");

        response.then().statusCode(500);
    }
}