package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        BASE = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080/rest");
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testAlphaCodeNotFound_mapsTo404() {
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNameNotFound_mapsTo404() {
        Response act = given().when().get("/v1/name/123");
        assertEquals(404, act.getStatusCode());
    }
}