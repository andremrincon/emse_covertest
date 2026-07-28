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
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateThrowsException() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "First description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Second description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductNoConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithActiveConfiguration() {
        String productName = "test-prod-" + UUID.randomUUID();
        String featureName = "test-feat-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String requiredFeature = "req-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

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
    public void testAddExcludesConstraintToProduct() {
        String productName = "test-prod-" + UUID.randomUUID();
        String sourceFeature = "src-feat-" + UUID.randomUUID();
        String excludedFeature = "exc-feat-" + UUID.randomUUID();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/" + productName + "/constraints/excludes")
        .then()
            .statusCode(201);
    }
}