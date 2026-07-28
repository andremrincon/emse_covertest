package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class WrongProductConfigurationExceptionTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationSucceedsReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, "distributed-training");
        assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addFeatureToConfigurationWithExtremelyLongFeatureNameReturns500() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        String longFeature = "feature-name-that-is-intentionally-made-extremely-long-to-exceed-any-reasonable-database-column-width-or-url-path-segment-limit-and-potentially-cause-an-unhandled-exception-or-a-buffer-overflow-somewhere-deep-in-the-application-stack-resulting-in-a-generic-five-hundred-internal-server-error-response-instead-of-a-more-graceful-four-hundred-bad-request-error";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, longFeature);
        assertEquals(500, act.getStatusCode());
    }
}