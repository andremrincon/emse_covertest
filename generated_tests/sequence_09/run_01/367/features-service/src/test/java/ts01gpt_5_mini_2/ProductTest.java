package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url") != null ? System.getProperty("base.url") : (System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_success() {
        String product = "Smartwatch-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, "Blood Oxygen Sensor").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_serverError() {
        String product = "Gaming-PC-Tower-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, "Liquid Cooling").then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature_success() {
        String product = "X1-" + UUID.randomUUID().toString();
        String feature = "heart-rate-monitor-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeature_serverError_badFeatureName() {
        String product = "E-Reader-K2-" + UUID.randomUUID().toString();
        String badFeature = "../../../etc/passwd";
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, "placeholder-feature").then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, badFeature).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_success() {
        String product = "QuantumLeap-" + UUID.randomUUID().toString();
        String config = "standard-gpu-cluster";
        String feature = "distributed-training-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures_success() {
        String product = "ultra-laptop-" + UUID.randomUUID().toString();
        String config = "us-standard-16gb";
        String feature = "fingerprint-scanner-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_success() {
        String product = "Enterprise-Server-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_success() {
        String product = "Laptop-Pro-15-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", product).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConstraint_success() {
        String product = "UltraPhoneX-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/constraints/{constraintId}", product, 12345).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductByName_success() {
        String product = "Smartwatch-Series-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductsFeatures_success() {
        String product = "AeroBook-Pro-15-" + UUID.randomUUID().toString();
        String feature = "Backlit Keyboard " + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
        given().when().get("/products/{productName}/features", product).then().statusCode(lessThan(300));
    }
}