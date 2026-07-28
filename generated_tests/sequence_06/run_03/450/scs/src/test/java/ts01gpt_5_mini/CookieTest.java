package ts01gpt_5_mini;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUserIdValidReturnsOne() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "other", "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionExactMatchReturnsOne() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "nope", "local").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchReturnsTwo() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12", "site.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "example.com");
        resp.then().assertThat().body(equalTo("2"));
    }
}