package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URI");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        Response resp = given().when().post("/products/{productName}", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getProductFeatures_includesAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        List<?> namesA = resp.jsonPath().getList("featureName");
        List<?> namesB = resp.jsonPath().getList("name");
        List<Object> combined = new ArrayList<Object>();
        if (namesA != null) combined.addAll(namesA);
        if (namesB != null) combined.addAll(namesB);
        assertTrue(combined.contains(featureName));
    }

    @Test(timeout = 60000)
    public void createConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void activateFeatureOnConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_containsFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        List<?> list = resp.jsonPath().getList("");
        assertTrue(list.contains(featureName));
    }

    @Test(timeout = 60000)
    public void deactivateFeatureOnConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_afterDeactivation_notContains() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName);
        List<?> list = resp.jsonPath().getList("");
        assertFalse(list.contains(featureName));
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromProduct_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getConfigurationsForProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations", productName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getConfigurationByName_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        assertEquals(200, resp.getStatusCode());
    }
}