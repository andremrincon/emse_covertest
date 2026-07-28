package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridBranchReturnsOneWhenValStartsWithUserAndLongEnough() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "UserID", "Userabcd1", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridBranchReturnsZeroWhenValTooShort() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "USERid", "user12", "localhost");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionBranchReturnsOneForAmAndAbcCom() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "SESSION", "AM", "ABC.COM");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionBranchReturnsTwoForOtherSessionValues() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "example.com");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "unknownName", "someValue", "SomeSite");
        resp.then().body(equalTo("0"));
    }
}