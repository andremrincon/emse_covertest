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
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNoMatchReturns0() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "abcdefg", "xyz");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFoundPatReturns1() {
        given().when().get("/api/pat/{txt}", "setup-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABAByy", "ABAB");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFoundReverseReturns2() {
        given().when().get("/api/pat/{txt}", "seed-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxBABAyy", "ABAB");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABxxBABA", "ABAB");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatReverseAdjacentPalindromeReturnsIndex() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABBABA", "ABAB");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "BABAABAB", "ABAB");
        assertEquals("0", resp.getBody().asString());
    }
}