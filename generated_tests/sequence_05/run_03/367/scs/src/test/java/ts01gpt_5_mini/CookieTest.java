package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void userid_valStartingWithUserAndLong_returnsOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/userid/UserExtra123/example.com");
        assertEquals("1", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void userid_valShort_returnsZero() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/userid/Us1/localhost");
        assertEquals("0", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void session_withAmAndAbcDotCom_returnsOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/session/AM/ABC.COM");
        assertEquals("1", r.getBody().asString());
    }

    @Test(timeout = 60000)
    public void session_withNonAmOrDifferentSite_returnsTwo() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/cookie/session/pm/abc.com");
        assertEquals("2", r.getBody().asString());
    }
}