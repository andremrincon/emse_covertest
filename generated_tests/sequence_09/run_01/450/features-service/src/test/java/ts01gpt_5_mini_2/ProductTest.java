package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testAddFeatureReturns201() {
        String product = "prod-add-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/features/{featureName}", product, feature + "-new");
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesReturns200() {
        String product = "prod-list-" + UUID.randomUUID().toString();
        String feature = "listfeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String product = "prod-del-" + UUID.randomUUID().toString();
        String feature = "delfeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureReturns200() {
        String product = "prod-update-" + UUID.randomUUID().toString();
        String feature = "updatefeat-" + UUID.randomUUID().toString();
        String desc = "Description " + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().formParam("description", desc).when().put("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "prod-conf-add-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        String feature = "confFeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesReturns200() {
        String product = "prod-conf-list-" + UUID.randomUUID().toString();
        String config = "conf-list-" + UUID.randomUUID().toString();
        String feature = "confFeatList-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintReturns201() {
        String product = "prod-req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", product);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintReturns201() {
        String product = "prod-excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("sourceFeature", "CPU-i9-13900H").formParam("excludedFeature", "Integrated-Graphics-Only").when().post("/products/{productName}/constraints/excludes", product);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetProductContainsFeature() {
        String product = "prod-get-" + UUID.randomUUID().toString();
        String featureA = "buildfeatA-" + UUID.randomUUID().toString();
        String featureB = "buildfeatB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        assertTrue(act.getBody().asString().contains(featureA));
    }
}