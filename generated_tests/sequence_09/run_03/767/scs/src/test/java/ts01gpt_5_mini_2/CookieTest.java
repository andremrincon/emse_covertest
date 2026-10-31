package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("baseUrl");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridValidStartsWithUserReturns1() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/userid/userabc/example.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturns0() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/userid/user/example.com").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmOnAbcComReturns1() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/session/am/abc.com").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturns2() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/session/xyz/other.com").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturns0() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/someName/someVal/example.com").then().body(equalTo("0"));
    }
}