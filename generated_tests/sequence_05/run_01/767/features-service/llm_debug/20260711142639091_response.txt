package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private String baseUrl() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) return env;
        return System.getProperty("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnGetProduct() {
        String base = baseUrl();
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post(base + "/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/products/{productName}", product);
        act.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnOptionsPreflight() {
        String base = baseUrl();
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post(base + "/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().options(base + "/products/{productName}/features", product);
        act.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testCORSMaxAgeHeaderPresentOnPostFeature() {
        String base = baseUrl();
        String product = "test-product-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post(base + "/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().formParam("description", "desc").when().post(base + "/products/{productName}/features/{featureName}", product, feature);
        act.then().header("Access-Control-Max-Age", "3600");
    }
}