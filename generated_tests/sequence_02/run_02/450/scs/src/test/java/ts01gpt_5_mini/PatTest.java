package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void init() {
        String fromProp = System.getProperty("api.base");
        String fromEnv = System.getenv().getOrDefault("API_BASE", null);
        if (fromProp != null && !fromProp.isEmpty()) {
            RestAssured.baseURI = fromProp;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            RestAssured.baseURI = fromEnv;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "someText", "ab");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatternFoundOnlyReturnsOne() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABCyy", "ABC");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyReturnsTwo() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxCBAyy", "ABC");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatternThenReverseNonContiguousReturnsIndex() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABCxxCBA", "ABC");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testPatternThenReverseContiguousAtNonZeroIndexReturnsIndex() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABCCBA", "ABC");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatternNonContiguousReturnsIndexOfReverse() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "CBAxxABC", "ABC");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoMatchesReturnsZero() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcdefgh", "XYZ");
        assertEquals("0", resp.asString());
    }
}