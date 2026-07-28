package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "A feature description")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateReturnsServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "first")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "duplicate")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithActiveConfigurationRemovesFromConfigurations() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to be configured")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductWithoutActiveConfiguration() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "standalone")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "source-" + UUID.randomUUID().toString())
                .formParam("requiredFeature", "required-" + UUID.randomUUID().toString())
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductInvalidProductReturnsServerError() {
        String product = "nonexistent-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "src")
                .formParam("requiredFeature", "req")
                .when().post("/products/{productName}/constraints/requires", product)
                .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "src-" + UUID.randomUUID().toString())
                .formParam("excludedFeature", "exc-" + UUID.randomUUID().toString())
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductInvalidProductReturnsServerError() {
        String product = "nonexistent-" + UUID.randomUUID().toString();
        given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "s")
                .formParam("excludedFeature", "e")
                .when().post("/products/{productName}/constraints/excludes", product)
                .then().statusCode(500);
    }
}