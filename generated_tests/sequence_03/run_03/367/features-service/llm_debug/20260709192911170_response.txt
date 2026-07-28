package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/";
        }
        URL url = new URL(base);
        String protocolHost = url.getProtocol() + "://" + url.getHost();
        RestAssured.baseURI = protocolHost;
        int port = url.getPort();
        if (port == -1) {
            RestAssured.port = url.getProtocol().equals("https") ? 443 : 80;
        } else {
            RestAssured.port = port;
        }
        RestAssured.basePath = (url.getPath() == null || url.getPath().isEmpty()) ? "/" : url.getPath();
    }

    @Test(timeout = 60000)
    public void testCreateProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        Response act = given().pathParam("productName", productName)
                .when()
                .post("/products/{productName}");
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when()
                .post("/products/{productName}")
                .then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "desc")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when()
                .post("/products/{productName}/features/{featureName}");
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "initial")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "updated description")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().put("/products/{productName}/features/{featureName}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "to be deleted")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().delete("/products/{productName}/features/{featureName}");
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}");
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "feat for cfg")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesContainsAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "feat list")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().get("/products/{productName}/configurations/{configurationName}/features");
        act.then().body("", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "for delete cfg")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}");
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByNameReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().get("/products/{productName}/configurations/{configurationName}");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configA = "cfgA-" + UUID.randomUUID().toString();
        String configB = "cfgB-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configA)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configB)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .when().get("/products/{productName}/configurations");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String feature1 = "f1-" + UUID.randomUUID().toString();
        String feature2 = "f2-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "one")
                .pathParam("productName", productName)
                .pathParam("featureName", feature1)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "two")
                .pathParam("productName", productName)
                .pathParam("featureName", feature2)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .when().get("/products/{productName}/features");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivedFeatureNotPresentAfterDeletion() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        given().pathParam("productName", productName)
                .when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "to delete later")
                .pathParam("productName", productName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .pathParam("featureName", featureName)
                .when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", productName)
                .pathParam("configurationName", configName)
                .when().get("/products/{productName}/configurations/{configurationName}/features");
        act.then().body("", not(hasItem(featureName)));
    }
}