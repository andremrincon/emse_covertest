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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserReturnsOne() {
        given().when().get("/api/cookie/session/am/abc.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/session/other/example.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/other/someval/example.com").then().statusCode(lessThan(300));
        String uniqueVal = "user" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
        Response act = given().when().get("/api/cookie/userid/" + uniqueVal + "/example.com");
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortReturnsZero() {
        given().when().get("/api/cookie/other/xyz/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/userid/user1/example.com");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcComReturnsOne() {
        given().when().get("/api/cookie/userid/user1234/any.com").then().statusCode(lessThan(300));
        given().when().get("/api/cookie/userid/user1/any.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/session/am/abc.com");
        act.then().assertThat().body(equalTo("1"));
    }
}