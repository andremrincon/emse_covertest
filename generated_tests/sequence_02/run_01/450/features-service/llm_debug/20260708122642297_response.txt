package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private static final String BASE_URI;
    static {
        String uri = System.getProperty("api.base");
        if (uri == null || uri.isEmpty()) {
            uri = System.getenv("API_BASE");
        }
        if (uri == null || uri.isEmpty()) {
            uri = "http://localhost:8080";
        }
        BASE_URI = uri;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_createsActivedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().baseUri(BASE_URI).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().baseUri(BASE_URI).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returnsAddedFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().baseUri(BASE_URI).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));

        given().baseUri(BASE_URI).when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_exposesConfiguration() {
        String product = "prod-" + UUID.randomUUID().toString();
        String featureA = "featA-" + UUID.randomUUID().toString();
        String featureB = "featB-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().baseUri(BASE_URI).when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/features/{featureName}", product, featureA).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/features/{featureName}", product, featureB).then().statusCode(lessThan(300));
        given().baseUri(BASE_URI).when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().baseUri(BASE_URI).when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200);
    }
}