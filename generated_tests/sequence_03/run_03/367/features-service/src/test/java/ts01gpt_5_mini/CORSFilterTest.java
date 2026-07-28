package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        BASE = env;
    }

    @Test(timeout = 60000)
    public void testGetProduct_passesThroughFilter_chainCalled() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get(BASE + "/products/{productName}", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequest_returnsCorsAllowOriginHeader() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().options(BASE + "/products/{productName}/features", product).andReturn();
        assertEquals("*", r.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetFeatures_hasAccessControlAllowMethodsHeader() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post(BASE + "/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/products/{productName}/features", product).andReturn();
        assertEquals("POST, PUT, GET, OPTIONS, DELETE", r.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testOptionsRequest_setsMaxAgeHeader() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().options(BASE + "/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).andReturn();
        assertEquals("3600", r.getHeader("Access-Control-Max-Age"));
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete(BASE + "/products/{productName}", product).then().statusCode(204);
    }
}