package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ProductConfigurationTest {

    static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturns200() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        String pEnc = URLEncoder.encode(productName, "UTF-8");
        String fEnc = URLEncoder.encode(featureName, "UTF-8");
        String cEnc = URLEncoder.encode(configurationName, "UTF-8");
        given().when().post(BASE + "/products/" + pEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/features/" + fEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/configurations/" + cEnc).then().statusCode(lessThan(300));
        given().when().get(BASE + "/products/" + pEnc + "/configurations/" + cEnc).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        String pEnc = URLEncoder.encode(productName, "UTF-8");
        String fEnc = URLEncoder.encode(featureName, "UTF-8");
        String cEnc = URLEncoder.encode(configurationName, "UTF-8");
        given().when().post(BASE + "/products/" + pEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/features/" + fEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/configurations/" + cEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/configurations/" + cEnc + "/features/" + fEnc).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesContainsAddedFeature() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feature-" + UUID.randomUUID().toString();
        String configurationName = "config-" + UUID.randomUUID().toString();
        String pEnc = URLEncoder.encode(productName, "UTF-8");
        String fEnc = URLEncoder.encode(featureName, "UTF-8");
        String cEnc = URLEncoder.encode(configurationName, "UTF-8");
        given().when().post(BASE + "/products/" + pEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/features/" + fEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/configurations/" + cEnc).then().statusCode(lessThan(300));
        given().when().post(BASE + "/products/" + pEnc + "/configurations/" + cEnc + "/features/" + fEnc).then().statusCode(lessThan(300));
        given().when().get(BASE + "/products/" + pEnc + "/configurations/" + cEnc + "/features").then().body("$", hasItem(featureName));
    }
}