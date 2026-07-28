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
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUserid_returnsOne_whenValLongAndStartsWithUser() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        Response resp = given().when().get("/api/cookie/userid/user1234/example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserid_returnsZero_whenValShortOrNotLongEnough() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/userid/user1/example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSession_returnsOne_whenValAmAndSiteAbcCom() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/am/abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSession_returnsTwo_whenNotMatchingAmAndAbcCom() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/session/pm/other.example");
        resp.then().body(equalTo("2"));
    }
}