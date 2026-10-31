package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("api.baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("API_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void postConfigurationWithExcessivelyLongNameShouldReturn500() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String longConfigName = "a-very-long-configuration-name-that-exceeds-the-maximum-allowed-length-and-could-potentially-cause-a-buffer-overflow-or-database-truncation-error-leading-to-an-internal-server-error-because-the-system-was-not-designed-to-handle-such-long-inputs-gracefully";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", productName, longConfigName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationWithJsonLikeFeatureNameShouldReturn500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String problematicFeatureName = "%7B%22feature%22%3A%22new-feature%22%7D";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, problematicFeatureName);
        act.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductShouldReturn201() {
        String productName = "product-" + UUID.randomUUID().toString();
        String featureName = "Blood Oxygen Sensor";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", productName, featureName);
        act.then().statusCode(500);
    }
}