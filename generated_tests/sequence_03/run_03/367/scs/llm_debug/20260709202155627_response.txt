package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    private String baseUrl() {
        String b = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        return (b == null || b.isEmpty()) ? "http://localhost:8080" : b;
    }

    @Test(timeout = 60000)
    public void testUseridValueStartsWithUserReturnsOne() {
        String base = baseUrl();
        given().when().get(base + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String name = "UserId";
        String val = "UserXYZ123";
        String site = "Example.COM";
        Response act = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongValueNotStartingWithUserReturnsZero() {
        String base = baseUrl();
        given().when().get(base + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String name = "USERID";
        String val = "abcd12345";
        String site = "localhost";
        Response act = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        String base = baseUrl();
        given().when().get(base + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String name = "session";
        String val = "AM";
        String site = "ABC.COM";
        Response act = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("1", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturnsTwo() {
        String base = baseUrl();
        given().when().get(base + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String name = "session";
        String val = "pm";
        String site = "abc.com";
        Response act = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String base = baseUrl();
        given().when().get(base + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String name = "other" + unique;
        String val = "someValue";
        String site = "someSite";
        Response act = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        assertEquals("0", act.getBody().asString());
    }
}