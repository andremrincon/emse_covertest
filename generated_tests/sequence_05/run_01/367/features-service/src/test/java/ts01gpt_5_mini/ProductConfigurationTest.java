package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String id() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures_returns200() {
        String product = "prod-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddConfiguration_returns201() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConfiguration_isValid_trueByDefault() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures_containsActivatedFeature() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testConfiguration_hasActiveFeature_false_whenNotActivated() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", not(hasItem(feature)));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        String feature = "feat-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration_returns204() {
        String product = "prod-" + id();
        String config = "conf-" + id();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(204);
    }
}