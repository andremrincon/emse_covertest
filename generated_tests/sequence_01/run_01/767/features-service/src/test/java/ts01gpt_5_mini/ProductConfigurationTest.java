package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
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
    public void testRetrieveConfigurationReturns200() {
        String product = "p-" + UUID.randomUUID().toString();
        String config = "c-" + UUID.randomUUID().toString();
        String feature = "f-" + UUID.randomUUID().toString();
        Response r1 = given().when().post("/products/{productName}", product);
        Assert.assertTrue(r1.getStatusCode() >= 200 && r1.getStatusCode() < 300);
        Response r2 = given().formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertTrue(r2.getStatusCode() >= 200 && r2.getStatusCode() < 300);
        Response r3 = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertTrue(r3.getStatusCode() >= 200 && r3.getStatusCode() < 300);
        Response r4 = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertEquals(200, r4.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "p-" + UUID.randomUUID().toString();
        String config = "c-" + UUID.randomUUID().toString();
        String feature = "f-" + UUID.randomUUID().toString();
        Response r1 = given().when().post("/products/{productName}", product);
        Assert.assertTrue(r1.getStatusCode() >= 200 && r1.getStatusCode() < 300);
        Response r2 = given().formParam("description", "feature for config")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertTrue(r2.getStatusCode() >= 200 && r2.getStatusCode() < 300);
        Response r3 = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertTrue(r3.getStatusCode() >= 200 && r3.getStatusCode() < 300);
        Response r4 = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(201, r4.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String product = "p-" + UUID.randomUUID().toString();
        String config = "c-" + UUID.randomUUID().toString();
        String feature = "f-" + UUID.randomUUID().toString();
        Response r1 = given().when().post("/products/{productName}", product);
        Assert.assertTrue(r1.getStatusCode() >= 200 && r1.getStatusCode() < 300);
        Response r2 = given().formParam("description", "to be deleted")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertTrue(r2.getStatusCode() >= 200 && r2.getStatusCode() < 300);
        Response r3 = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertTrue(r3.getStatusCode() >= 200 && r3.getStatusCode() < 300);
        Response r4 = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertTrue(r4.getStatusCode() >= 200 && r4.getStatusCode() < 300);
        Response r5 = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(204, r5.getStatusCode());
    }
}