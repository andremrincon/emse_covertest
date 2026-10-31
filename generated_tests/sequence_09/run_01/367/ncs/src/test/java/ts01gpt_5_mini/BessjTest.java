package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL_ALT");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_axEqualsZero_returnsZero() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, "0.0");
        act.then().body(containsString("0.0"));
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, "10.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessOrEqualN_continuedFraction_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 10, "1e-10");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_signHandled_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, "-2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThanTwo_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 1, "2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_nNonNumeric_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", "abc", "2.5");
        act.then().statusCode(400);
    }
}