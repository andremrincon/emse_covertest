package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_SuccessCreatesFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "Test feature").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_DuplicateReturnsServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "Initial").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "Duplicate").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_RemovesFeatureFromConfigurationsAndReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "ToBeDeleted").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_NoConfigurationsStillDeletesAndReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("description", "Standalone").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_SuccessReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("requiredFeature", "req-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_SuccessReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().baseUri(baseUrl).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "src-" + UUID.randomUUID().toString()).formParam("excludedFeature", "excl-" + UUID.randomUUID().toString()).when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }
}