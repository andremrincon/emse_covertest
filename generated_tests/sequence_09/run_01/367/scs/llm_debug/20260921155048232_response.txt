package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/user1234/example.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/UserId/abcde/example.com");
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionExactMatchReturnsOne() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/am/abc.com");
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/other/abc.com");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/someName/someVal/example.com");
        resp.then().assertThat().body(equalTo("0"));
    }
}