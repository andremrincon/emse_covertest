package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidReturnsOne() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidSubstringReturnsZero() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcdefg", "localhost");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatchingReturnsOne() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "4.5", "-50.25").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "somevalue", "example.com");
        act.then().body(equalTo("0"));
    }
}