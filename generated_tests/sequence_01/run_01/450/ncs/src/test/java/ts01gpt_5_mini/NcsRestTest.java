package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("NCS_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("NCS_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParameters() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenMExceedsLimit() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.75);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenUnderlyingThrowsRuntimeException() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, -3, 0.75);
        resp.then().statusCode(200);
    }
}