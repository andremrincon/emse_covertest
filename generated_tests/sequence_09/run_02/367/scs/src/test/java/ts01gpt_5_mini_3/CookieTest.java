package ts01gpt_5_mini_3;

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
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueLongAndStartsWithUser() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", uniq, "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValueTooShortOrNotStartingWithUser() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session", uniq, "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr01", "localhost");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneWhenValAmAndSiteAbcCom() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", uniq, "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoWhenNonMatchingValOrSite() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "userid", uniq, "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "example.com");
        act.then().body(equalTo("2"));
    }
}