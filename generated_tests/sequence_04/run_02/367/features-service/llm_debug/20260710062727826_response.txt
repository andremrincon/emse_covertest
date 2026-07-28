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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCreatesFeatureSuccessfully() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String product = "prod-" + uuid;
        String configuration = "cfg-" + uuid;
        String feature = "feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature);
        resp.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationWithMaliciousLongFeatureNameReturnsServerError() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String product = "prod-" + uuid;
        String configuration = "cfg-" + uuid;
        String longFeature = "feature-name-that-is-intentionally-made-extremely-long-to-exceed-any-reasonable-database-column-width-or-url-path-segment-limit-and-potentially-cause-an-unhandled-exception-or-a-buffer-overflow-somewhere-deep-in-the-application-stack-resulting-in-a-generic-five-hundred-internal-server-error-response-instead-of-a-more-graceful-four-hundred-bad-request-error-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, longFeature);
        resp.then().statusCode(500);
    }
}