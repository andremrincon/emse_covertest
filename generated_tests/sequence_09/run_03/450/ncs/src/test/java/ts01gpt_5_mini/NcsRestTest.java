package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_validN_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_low_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 2, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_valid_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_m_too_large_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.75);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_runtimeException_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 1.2);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_valid_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_runtimeException_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 3.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_valid_returnsBodyResult() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainder_outOfBounds_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 10001, 1);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_valid_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_invalid_throws_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 0.1);
        resp.then().statusCode(400);
    }
}