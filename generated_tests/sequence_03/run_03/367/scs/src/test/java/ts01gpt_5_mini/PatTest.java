package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPat_PatLengthTwo_Returns0() {
        given().when().get("/api/pat/{txt}", "setup-short").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "hello", "ab");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatFound_NoReverse_Returns1() {
        given().when().get("/api/pat/{txt}", "setup-found").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "XYZABCDEF", "ABC");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseFound_NoPat_Returns2() {
        given().when().get("/api/pat/{txt}", "setup-rev").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ZZZCBAZZ", "ABC");
        assertEquals("2", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatAndReverse_NonAdjacent_ReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup-nonadj").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABCxxxCBA", "ABC");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatFollowedByReverse_AdjacentPalindrome_ReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup-pal").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "ABCCBA", "ABC");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseThenPat_NonAdjacent_ReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup-revthenpat").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", "CBAxxABC", "ABC");
        assertEquals("0", act.asString());
    }
}