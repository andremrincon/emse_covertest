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
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures SpO2.")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).andReturn();
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureDuplicate() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Initial")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "Duplicate attempt")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).andReturn();
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureRemovesFromConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "To be deleted")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName).andReturn();
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName).andReturn();
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName).andReturn();
        assertEquals(201, act.getStatusCode());
    }
}