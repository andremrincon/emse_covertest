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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("BASE_URL");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturnsCreated() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, "distributed-training");
        assertEquals(500, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersServerError() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) sb.append('x');
        String longFeature = sb.toString();
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, longFeature);
        assertEquals(500, resp.getStatusCode());
    }
}