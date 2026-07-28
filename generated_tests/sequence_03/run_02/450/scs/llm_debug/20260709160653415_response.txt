package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    private static final String BASE;
    static {
        String env = System.getenv("TEST_BASE_URL");
        String prop = System.getProperty("test.base.url");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUserReturnsOne() {
        String unique = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", unique + "xyz", "example.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "usr", "localhost");
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatchingValueAndSiteReturnsOneCaseInsensitive() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "SESSION", "AM", "AbC.CoM");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "nope", "example.com");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String unique = "x" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        given().when().get(BASE + "/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "random", unique, "example.com");
        resp.then().assertThat().body(equalTo("0"));
    }
}