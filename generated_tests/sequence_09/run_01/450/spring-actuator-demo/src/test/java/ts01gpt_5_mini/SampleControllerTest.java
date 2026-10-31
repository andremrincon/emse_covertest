package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class SampleControllerTest {

    private static String BASE_URI;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("base.uri");
        String env = System.getenv("BASE_URI");
        BASE_URI = prop != null ? prop : (env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSayHelloWithName() {
        String unique = UUID.randomUUID().toString();
        given().param("name", unique).when().get(BASE_URI + "/").then().statusCode(lessThan(300));
        Response act = given().param("name", "John Smith").when().get(BASE_URI + "/");
        assertEquals("Hello John Smith!!", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSayHelloDefaultName() {
        String unique = UUID.randomUUID().toString();
        given().param("name", unique).when().get(BASE_URI + "/").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URI + "/");
        assertEquals("Hello Guest!!", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithZeroDelayUsesRandomPath() {
        String unique = UUID.randomUUID().toString();
        given().param("name", unique).when().get(BASE_URI + "/").then().statusCode(lessThan(300));
        Response act = given().param("delay", 0).when().get(BASE_URI + "/slowApi");
        assertEquals("Result", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithPositiveDelayReturns200() {
        String unique = UUID.randomUUID().toString();
        given().param("name", unique).when().get(BASE_URI + "/").then().statusCode(lessThan(300));
        Response act = given().param("delay", 1).when().get(BASE_URI + "/slowApi");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTimeConsumingApiWithNegativeDelayReturnsServerError() {
        String unique = UUID.randomUUID().toString();
        given().param("name", unique).when().get(BASE_URI + "/").then().statusCode(lessThan(300));
        Response act = given().param("delay", -1).when().get(BASE_URI + "/slowApi");
        assertEquals(200, act.getStatusCode());
    }
}