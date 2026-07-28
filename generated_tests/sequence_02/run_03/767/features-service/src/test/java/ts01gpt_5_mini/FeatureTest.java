package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_success() {
        String pid = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures oxygen").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String pid = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Desc").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", pid).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct_success() {
        String pid = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "Initial").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "RGB backlit keyboard").when().put("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_success() {
        String pid = "prod-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "To delete").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_success() {
        String pid = "prod-" + UUID.randomUUID();
        String cfg = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg feature").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", pid, cfg, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_success() {
        String pid = "prod-" + UUID.randomUUID();
        String cfg = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "for cfg").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", pid, cfg, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", pid, cfg, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String pid = "prod-" + UUID.randomUUID();
        String cfg = "cfg-" + UUID.randomUUID();
        String feature = "feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "active").when().post("/products/{productName}/features/{featureName}", pid, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", pid, cfg, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", pid, cfg).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct_returns200() {
        String pid = "prod-" + UUID.randomUUID();
        String cfg = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", pid).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameForProduct_returns200() {
        String pid = "prod-" + UUID.randomUUID();
        String cfg = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", pid, cfg).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_success() {
        String pid = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", pid).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_success() {
        String pid = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", pid).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", pid).then().statusCode(201);
    }
}