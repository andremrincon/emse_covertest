package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

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
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=Auto-created feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddingSameFeatureTwice_returns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "dup-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=first create").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=second create duplicate").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureNameToDifferentProduct_returns201() {
        String productA = "prodA-" + UUID.randomUUID().toString();
        String productB = "prodB-" + UUID.randomUUID().toString();
        String feature = "shared-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productB).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=feature for A").when().post("/products/{productName}/features/{featureName}", productA, feature).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=feature for B").when().post("/products/{productName}/features/{featureName}", productB, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "upd-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=initial").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=RGB backlit keyboard with customizable zones and per-key lighting.").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "del-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=to be deleted").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "cfgfeat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=feature for configuration").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "cfgdel-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=feature for config delete").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "list-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().header("Content-Type", "application/x-www-form-urlencoded").body("description=for listing").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }
}