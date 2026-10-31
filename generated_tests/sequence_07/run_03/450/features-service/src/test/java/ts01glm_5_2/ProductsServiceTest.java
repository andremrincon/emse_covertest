package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

import java.util.UUID;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductWithoutDescription() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicate() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "First description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Second description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(not(201));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductNoConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature")
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
    public void testDeleteFeatureOfProductWithConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "conf-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test feature")
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
    public void testAddRequiresConstraintToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void testAddExcludesConstraintToProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

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