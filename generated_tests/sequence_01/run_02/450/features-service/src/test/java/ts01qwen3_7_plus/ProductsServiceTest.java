package ts01qwen3_7_plus;

import io.restassured.RestAssured;
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
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_Duplicate() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_ActiveInConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NotActiveInConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
        .then()
            .statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_Success() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String excludedFeature = "Excluded-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
        .then()
            .statusCode(lessThan(300));
        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("description", "desc")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
        .then()
            .statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }
}