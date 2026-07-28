package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() throws MalformedURLException {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        URL url = new URL(base);
        RestAssured.baseURI = url.getProtocol() + "://" + url.getHost();
        int port = url.getPort();
        if (port == -1) {
            if ("https".equalsIgnoreCase(url.getProtocol())) {
                RestAssured.port = 443;
            } else {
                RestAssured.port = 80;
            }
        } else {
            RestAssured.port = port;
        }
        RestAssured.basePath = url.getPath().equals("") ? "/" : url.getPath();
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasNoFeatures() {
        String productName = "prod-empty-" + UUID.randomUUID().toString();
        String configName = "cfg-empty-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationActivatesFeature() {
        String productName = "prod-activate-" + UUID.randomUUID().toString();
        String featureName = "feat-activate-" + UUID.randomUUID().toString();
        String configName = "cfg-activate-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(201);
    }
}