package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void userid_with_long_val_starting_user_returns_1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "UserXYZ1", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void userid_with_long_val_not_starting_user_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcdxyz1", "example.com");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void userid_with_short_val_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr1", "example.com");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void session_with_am_and_abc_dot_com_returns_1() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void session_with_other_values_returns_2() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "bm", "abc.com");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void non_userid_non_session_returns_0() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "anything", "whatever");
        act.then().body(equalTo("0"));
    }
}