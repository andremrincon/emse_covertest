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
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAlphaCodeNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNameNumericNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCurrencyNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        assertEquals(404, resp.getStatusCode());
    }
}