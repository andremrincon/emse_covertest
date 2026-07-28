package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserLong() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "UserId", "UserXYZ123", "EXAMPLE.COM").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValReturnsZero() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "USERID", "user1", "example.com").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatchReturnsOne() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "SESSION", "AM", "ABC.com").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchReturnsTwo() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/{name}/{val}/{site}", "something", "whatever", "site.com").then().assertThat().body(equalTo("0"));
    }
}