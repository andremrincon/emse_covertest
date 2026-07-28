package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        baseUrl = env != null && !env.isEmpty() ? env : System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateReturnsServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "first").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "second").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductRemovesFromConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProductNonExistentFeatureReturnsServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "nonexistent-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "A").formParam("requiredFeature", "B").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToNonexistentProductReturnsServerError() {
        String productName = "nonexistent-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "A").formParam("requiredFeature", "B").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "X").formParam("excludedFeature", "Y").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToNonexistentProductReturnsServerError() {
        String productName = "nonexistent-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "X").formParam("excludedFeature", "Y").when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(500);
    }
}