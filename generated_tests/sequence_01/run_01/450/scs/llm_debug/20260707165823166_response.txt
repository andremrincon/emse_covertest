package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidReturnsOne() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/userid/user12345/example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridTooShortReturnsZero() {
        given().when().get("/api/pat/healthcheck2").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/userid/user1/example.com").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionExactReturnsOne() {
        given().when().get("/api/pat/healthcheck3").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/session/am/abc.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        given().when().get("/api/pat/healthcheck4").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/session/xyz/example.com").then().body(equalTo("2"));
    }
}