package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_returns200() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", productName);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeature_returns200() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("description", "Updated description " + uuid).when().put("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeature_returns204() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", productName, featureName);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configName = "conf-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configName = "conf-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_containsFeature() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        String configName = "conf-" + uuid;
        String featureName = "feat-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName);
        resp.then().body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void getProductByName_returns200() {
        String uuid = UUID.randomUUID().toString();
        String productName = "prod-" + uuid;
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", productName);
        resp.then().statusCode(200);
    }
}