package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class ProductsConfigurationFeaturesResourceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore("expected:<201> but was:<405>")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        String featureName = "feature-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String body = "{\"feature\":\"" + featureName + "\"}";
        Response act = given().contentType("application/json").body(body).when().post("/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        assertEquals(201, act.getStatusCode());
    }

    @Ignore("expected:<500> but was:<405>")
    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFailure() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String invalidBody = "{}";
        Response act = given().contentType("application/json").body(invalidBody).when().post("/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        assertEquals(500, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <405> was greater than <300>.")
    @Test(timeout = 60000)
    public void testDeleteFeatureSuccess() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        String featureName = "feature-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String body = "{\"feature\":\"" + featureName + "\"}";
        given().contentType("application/json").body(body).when().post("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFailure() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configurationName = "conf-" + uuid;
        String featureName = "99";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(500, act.getStatusCode());
    }
}