package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ProductConfigurationTest {

    private static final String BASE;
    static {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        BASE = cfg;
    }

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturns200_whenProductHasFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();
        String config = "config-" + UUID.randomUUID().toString();

        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).formParam("description", "descA").when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().baseUri(BASE).formParam("description", "descB").when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().baseUri(BASE).when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201_onSuccessfulActivation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();

        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().baseUri(BASE).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConfigurationActivedFeatures_containsActivatedFeature_afterActivation() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "activated-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().baseUri(BASE).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE).formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().baseUri(BASE).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));

        given().baseUri(BASE).when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(feature));
    }
}