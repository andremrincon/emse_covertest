package ts01gpt_5_mini;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class NotFoundExceptionMapperTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("rest.base.url");
        String env = System.getenv("REST_BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            base = prop;
        } else if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            base = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testAlphaEndpointNotFound() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testUnknownPathTriggersNotFound() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response act = given().when().get(base + "/nonexistent/" + unique);
        act.then().statusCode(404);
    }
}