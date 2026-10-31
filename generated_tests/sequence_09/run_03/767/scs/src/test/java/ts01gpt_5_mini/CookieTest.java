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
    public static void setUp() {
        String cfg = System.getProperty("TEST_BASE_URL");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUser() {
        given().when().get("/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/userabc/example.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidTooShort() {
        given().when().get("/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/usr/example.com");
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionExactMatch() {
        given().when().get("/api/cookie/userid/userabc/example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/am/abc.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatching() {
        given().when().get("/api/cookie/userid/usr/example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/bad/example.com");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityProducesSameResult() {
        given().when().get("/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/UsErId/UsErAbC/EXAMPLE.COM");
        resp.then().assertThat().body(equalTo("1"));
    }
}