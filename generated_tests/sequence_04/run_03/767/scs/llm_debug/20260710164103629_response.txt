package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_userid_prefix_match_returns_1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_userid_short_value_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr12", "localhost");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_userid_long_nonmatching_prefix_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcdefg", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_session_exact_match_returns_1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_session_nonmatching_site_returns_2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "example.com");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_other_name_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "othername", "anything", "anysite");
        resp.then().body(equalTo("0"));
    }
}