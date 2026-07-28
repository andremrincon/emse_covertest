package ts01gpt_5_mini;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Before;
import org.junit.Test;

public class RegexTest {

    @Before
    public void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    private String enc(String s) {
        return s.replace("%", "%25").replace("/", "%2F").replace("+", "%2B");
    }

    @Test(timeout = 60000)
    public void testPatReturns200ForUrl() {
        String txt = enc("http://abc/def");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatReturns200ForDate() {
        String txt = enc("mon12jan");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatReturns200ForFpe() {
        String txt = enc("12.3e+45");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatReturns200ForNone() {
        String txt = enc("xyz");
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", txt).then().statusCode(200);
    }
}