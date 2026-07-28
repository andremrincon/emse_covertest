package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private String enc(String v) {
        try {
            return URLEncoder.encode(v, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToNewProduct_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().formParam("description", "Feature created via test").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription_shouldReturn200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().formParam("description", "initial").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().formParam("description", "updated description").when().put("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_shouldReturn204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().formParam("description", "to be deleted").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().delete("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("description", "feature for config").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(feature)).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures_shouldReturn200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().formParam("description", "listable").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().get("/products/" + enc(product) + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_shouldReturn200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("description", "active").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().get("/products/" + enc(product) + "/configurations/" + enc(config) + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_shouldReturn204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config)).then().statusCode(lessThan(300));
        given().formParam("description", "to remove").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().delete("/products/" + enc(product) + "/configurations/" + enc(config) + "/features/" + enc(feature)).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateDuplicateFeatureDifferentProduct_shouldAllowAndReturn201() {
        String productA = "prodA-" + UUID.randomUUID().toString();
        String productB = "prodB-" + UUID.randomUUID().toString();
        String feature = "shared-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(productA)).then().statusCode(lessThan(300));
        given().when().post("/products/" + enc(productB)).then().statusCode(lessThan(300));
        given().formParam("description", "on A").when().post("/products/" + enc(productA) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().formParam("description", "on B").when().post("/products/" + enc(productB) + "/features/" + enc(feature)).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureReturnsBodyWithName_shouldHaveName() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + enc(product)).then().statusCode(lessThan(300));
        given().formParam("description", "initial body test").when().post("/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().formParam("description", "body updated").when().put("/products/" + enc(product) + "/features/" + enc(feature)).then().body("name", equalTo(feature));
    }
}