package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    void createProductArrange(String productName) {
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
    }

    void addFeatureArrange(String productName, String featureName, String description) {
        given().formParam("description", description).when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
    }

    void createConfigurationArrange(String productName, String configurationName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_createsFeature201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        Response act = given().formParam("description", "Test feature description").when().post("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        addFeatureArrange(product, feature, "desc");
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        addFeatureArrange(product, feature, "initial");
        Response act = given().formParam("description", "updated description").when().put("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        addFeatureArrange(product, feature, "to be deleted");
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        createProductArrange(product);
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        createConfigurationArrange(product, config);
        addFeatureArrange(product, feature, "feature for config");
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        createConfigurationArrange(product, config);
        addFeatureArrange(product, feature, "feature to config");
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        createProductArrange(product);
        createConfigurationArrange(product, config);
        addFeatureArrange(product, feature, "active feature");
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByName_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        createProductArrange(product);
        Response act = given().when().get("/products/{productName}", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts_returns200() {
        Response act = given().when().get("/products");
        act.then().statusCode(200);
    }
}