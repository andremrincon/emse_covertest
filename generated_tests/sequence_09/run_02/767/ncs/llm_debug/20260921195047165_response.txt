package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE;
    static {
        String b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) {
            b = System.getProperty("baseUrl", "http://localhost:8080");
        }
        BASE = b;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testBessjValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooSmall() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 2, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooLarge() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1001, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidLargeM() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.75);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidLargeN() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 1001, 0.75);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidTooLargePositive() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 10001, 1);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidTooLargeNegative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -10001, 1);
        resp.then().statusCode(400);
    }
}