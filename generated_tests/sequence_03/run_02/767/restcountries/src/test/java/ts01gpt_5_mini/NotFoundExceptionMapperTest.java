package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAlphaEndpointNotFoundTriggersNotFoundExceptionMapper() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNameEndpointNotFoundTriggersNotFoundExceptionMapper() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        assertEquals(404, act.getStatusCode());
    }
}