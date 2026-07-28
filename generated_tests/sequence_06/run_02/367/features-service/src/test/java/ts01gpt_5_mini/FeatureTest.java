package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String desc = "Description " + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", desc).when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void getFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{product}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void updateFeatureOfProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated desc").when().put("/products/{product}/features/{feature}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void deleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to delete").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{product}/features/{feature}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "fdesc").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}/features/{feature}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "fdesc").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}/features/{feature}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{product}/configurations/{configuration}/features/{feature}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_containsPreviouslyAddedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "fdesc").when().post("/products/{product}/features/{feature}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}/features/{feature}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{product}/configurations/{configuration}/features", product, config).then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void addConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{product}/configurations/{configuration}", product, config).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void createAndDeleteProduct_returns204OnDelete() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{product}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{product}", product).then().statusCode(204);
    }
}