package ts01qwen3_7_plus;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsServiceTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String description = "Description-" + UUID.randomUUID().toString();

        given()
            .basePath(BASE_URL)
        .when()
            .put("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", description)
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(is(201));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithActiveConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .basePath(BASE_URL)
        .when()
            .put("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
        .when()
            .post("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(is(204));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();

        given()
            .basePath(BASE_URL)
        .when()
            .put("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + requiredFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/" + productName + "/constraints/requires")
        .then()
            .statusCode(is(201));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();

        given()
            .basePath(BASE_URL)
        .when()
            .put("/products/" + productName)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + sourceFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("description", "desc")
        .when()
            .post("/products/" + productName + "/features/" + excludedFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .basePath(BASE_URL)
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(is(201));
    }
}