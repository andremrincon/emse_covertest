package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "to delete").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures_containsFeatureName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "exists").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductContainsFeature_viaProductEndpoint_bodyContainsFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "one").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        act.then().body(containsString(feature));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "src-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "s").when().post("/products/{productName}/features/{featureName}", product, sourceFeature).then().statusCode(lessThan(300));
        given().formParam("description", "r").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_missingSourceFeature_returns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String missingSource = "missing-" + UUID.randomUUID().toString();
        String requiredFeature = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "r").when().post("/products/{productName}/features/{featureName}", product, requiredFeature).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", missingSource).formParam("requiredFeature", requiredFeature).when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }
}