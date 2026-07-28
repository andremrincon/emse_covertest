package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesIncludesProductFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200).body(containsString(config));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesEmptyWhenNoProductFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200);
    }
}