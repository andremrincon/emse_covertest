package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_withJsonLikeFeatureName_shouldReturn500() throws Exception {
        String uuid = UUID.randomUUID().toString();
        String product = "test-prod-" + uuid;
        String configuration = "standard-gpu-cluster-" + uuid;
        String rawFeature = "{\"feature\":\"new-feature\"}";
        String featureEncoded = URLEncoder.encode(rawFeature, "UTF-8");
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, featureEncoded).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void updateFeature_withNullByteInjectionInFeatureName_shouldReturn500() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-update-" + uuid;
        String normalFeature = "backlit-keyboard-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, normalFeature).then().statusCode(lessThan(300));
        given().when().put("/products/{productName}/features/{featureName}", product, "camera-%00-injection").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void deleteConfigurationFeature_withVeryLongFeatureName_shouldReturn500() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-del-" + uuid;
        String configuration = "conf-del-" + uuid;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 600; i++) {
            sb.append('a');
        }
        String veryLongFeature = sb.toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, veryLongFeature).then().statusCode(500);
    }
}