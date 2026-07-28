package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String base = System.getProperty("baseUrl", env != null ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "irrelevant-" + uuid, "ab");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatternFoundOnlyReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "XYZABCDEF-" + uuid, "ABC");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "XYZCBADEF-" + uuid, "ABC");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPalindromeAdjacentReturnsIndexZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep2-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABAABA-" + uuid, "ABA");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatternReturnsIndexOfReverse() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep3-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "CBAxxxABC-" + uuid, "ABC");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoMatchReturnsZeroForLongPattern() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "prep4-" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "XXXXXXXX-" + uuid, "ABC");
        assertEquals("0", resp.asString());
    }
}