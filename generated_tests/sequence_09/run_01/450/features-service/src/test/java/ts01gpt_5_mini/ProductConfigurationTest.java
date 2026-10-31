package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateProduct_shouldReturn201() {
        String product = "prod-create-" + UUID.randomUUID();
        Response act = given().when().post("/products/{productName}", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_shouldReturn201() {
        String product = "prod-feature-" + UUID.randomUUID();
        String feature = "Feature-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_shouldReturn200() {
        String product = "prod-update-" + UUID.randomUUID();
        String feature = "backlit-keyboard-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
                .when().put("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_shouldReturn204() {
        String product = "prod-delete-" + UUID.randomUUID();
        String feature = "heart-rate-monitor-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "temp")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration_shouldReturn201() {
        String product = "prod-config-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_shouldReturn201() {
        String product = "prod-config-feature-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        String feature = "distributed-training-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_shouldContainFeature() {
        String product = "prod-get-actived-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        String feature = "fingerprint-scanner-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        act.then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration_shouldReturn204() {
        String product = "prod-remove-conf-feature-" + UUID.randomUUID();
        String config = "conf-" + UUID.randomUUID();
        String feature = "gps-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_shouldReturn200() {
        String product = "AeroBook-Pro-15-" + UUID.randomUUID();
        String f1 = "FeatureA-" + UUID.randomUUID();
        String f2 = "FeatureB-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d1")
                .when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d2")
                .when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint_shouldReturn201() {
        String product = "Enterprise-Server-X1-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when().post("/products/{productName}/constraints/requires", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint_shouldReturn201() {
        String product = "Laptop-Pro-15-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when().post("/products/{productName}/constraints/excludes", product);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByName_shouldReturn200() {
        String product = "prod-get-config-" + UUID.randomUUID();
        String config = "production-us-east-1-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().statusCode(200);
    }
}