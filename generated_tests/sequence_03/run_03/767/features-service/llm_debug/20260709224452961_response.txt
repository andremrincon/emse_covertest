package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String description = "desc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", description).when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductDuplicateProducesServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String description = "desc-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", description).when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "duplicate-" + description).when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureRemovesFromConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureWhenNoConfigurations() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProductSuccess() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String excludedFeature = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }
}