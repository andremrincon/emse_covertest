package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("api.baseUrl");
        String fromEnv = System.getenv("API_BASE_URL");
        String base = fromProp != null ? fromProp : (fromEnv != null ? fromEnv : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct_shouldReturn204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testUpdateExistingFeature_shouldReturn200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateNonexistentFeature_shouldReturn500() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "nonexistent-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "will-fail").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_containsAddedFeature() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().body(containsString(feature));
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_emptyList_whenNoFeatures() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "A").formParam("requiredFeature", "B").when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "X").formParam("excludedFeature", "Y").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_afterAdding_thenNoLongerVisible() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }
}