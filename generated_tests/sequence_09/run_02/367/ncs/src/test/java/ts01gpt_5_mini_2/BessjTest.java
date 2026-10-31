package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN_ShouldReturn400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", -5, 2.5);
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjAxZero_ShouldReturn200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanN_ShouldReturn200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjAxVerySmall_LooksUpwardRecurrence_ShouldReturn200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 5, 1e-10);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjNegativeX_OddN_SignFlipBehavior_ShouldReturn200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        assertEquals(200, act.getStatusCode());
    }
}