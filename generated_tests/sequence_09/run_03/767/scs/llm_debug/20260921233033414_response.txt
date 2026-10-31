package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPat_serviceAvailable_pingEndpoint() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/The");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_patLengthTwo_returnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "abcdef", "ab");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_patAndReverseContiguous_returnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABABBABA", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_reverseThenPatContiguous_returnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "BABAABAB", "ABAB");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_noMatch_returnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "abcdefg", "xyz");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat_txtOnlyEndpoint_returnsOkBody() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "TheQuickBrownFox");
        act.then().statusCode(200);
    }
}