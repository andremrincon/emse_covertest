package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessOrEqual_negativeX_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_largeNegativeX_triggersEdgePath_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 12, 13).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/{n}/{x}", 5, -10.0);
        r.then().statusCode(200);
    }
}