package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_activatesFeatureByName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_returnsValidTrue() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config).when().get("/products/{productName}/configurations/{configurationName}").then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_containsFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config).when().get("/products/{productName}/configurations/{configurationName}/features").then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_deactivates_feature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("configurationName", config).pathParam("featureName", feature).when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returnsCreated() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "Test feature description").when().post("/products/{productName}/features/{featureName}").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("featureName", feature).formParam("description", "Updated description").when().put("/products/{productName}/features/{featureName}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).when().get("/products/{productName}/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductFeature_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", product).pathParam("featureName", feature).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).pathParam("featureName", feature).when().delete("/products/{productName}/features/{featureName}").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();

        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));

        given().pathParam("productName", product).when().delete("/products/{productName}").then().statusCode(204);
    }
}