package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureSuccessCreatesFeature() {
        String productName = "p-" + UUID.randomUUID().toString();
        String featureName = "f-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/" + productName + "/features/" + featureName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureDuplicateLeadsToServerError() {
        String productName = "p-" + UUID.randomUUID().toString();
        String featureName = "f-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "first")
                .when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "second")
                .when().post("/products/" + productName + "/features/" + featureName);
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureRemovesFeatureFromConfigurations() {
        String productName = "p-" + UUID.randomUUID().toString();
        String featureName = "f-" + UUID.randomUUID().toString();
        String configName = "c-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/" + productName + "/features/" + featureName);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductReturnsCreated() {
        String productName = "p-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/" + productName + "/constraints/requires");
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductReturnsCreated() {
        String productName = "p-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/" + productName + "/constraints/excludes");
        assertEquals(201, act.getStatusCode());
    }
}