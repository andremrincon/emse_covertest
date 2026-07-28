package ts01glm_5_2;

import io.restassured.RestAssured;
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

    @Test(timeout = 60000)
    public void addFeatureToProduct_success() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_duplicateThrowsException() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_noConfigurations() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_withActiveConfiguration() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addRequiresConstraintToProduct_success() {
        String productName = "test-product-" + UUID.randomUUID();
        String sourceFeature = "source-feature-" + UUID.randomUUID();
        String requiredFeature = "required-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addExcludesConstraintToProduct_success() {
        String productName = "test-product-" + UUID.randomUUID();
        String sourceFeature = "source-feature-" + UUID.randomUUID();
        String excludedFeature = "excluded-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
        .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
        .then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then()
            .statusCode(201);
    }
}