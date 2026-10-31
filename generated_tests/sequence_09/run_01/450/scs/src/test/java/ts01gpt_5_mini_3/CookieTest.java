package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValStartsWithUserAndLong() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", "notam", "example.com").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneForAmAndAbcCom() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoForNonMatchingValOrSite() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1", "example.org").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "xyz", "othersite.com");
        act.then().assertThat().body(equalTo("2"));
    }
}