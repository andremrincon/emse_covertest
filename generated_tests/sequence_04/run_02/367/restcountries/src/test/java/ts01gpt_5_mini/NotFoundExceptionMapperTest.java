package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

    private static String base() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            return env;
        }
        String prop = System.getProperty("base.url");
        if (prop != null && !prop.isEmpty()) {
            return prop;
        }
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testMapperHandlesUnknownPath() {
        String base = base();
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get(base + "/this-path-does-not-exist/" + uuid).then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testMapperHandlesAlphaNotFound() {
        String base = base();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(base + "/v1/alpha/XYZ").then().statusCode(404);
    }
}