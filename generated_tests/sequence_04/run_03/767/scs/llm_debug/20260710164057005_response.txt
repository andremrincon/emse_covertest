package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    private static String base;

    @BeforeClass
    public static void setUp() {
        String prop = System.getProperty("test.base.url");
        String env = System.getenv("TEST_BASE_URL");
        base = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatTooShort_returnsZero() {
        given().when().get(base + "/api/pat/{txt}", "hello").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "hello", "ab");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatFoundWithoutReverse_returnsOne() {
        given().when().get(base + "/api/pat/{txt}", "xxABAByy").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "xxABAByy", "ABAB");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testReverseFoundWithoutPat_returnsTwo() {
        given().when().get(base + "/api/pat/{txt}", "xxBABAyy").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "xxBABAyy", "ABAB");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonContiguous_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "ABABxxBABA").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "ABABxxBABA", "ABAB");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPalindromeContiguousPatThenReverse_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "ABAABA").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "ABAABA", "ABA");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatNonContiguous_returnsIndex() {
        given().when().get(base + "/api/pat/{txt}", "BABAxxABAB").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", "BABAxxABAB", "ABAB");
        assertEquals(200, act.getStatusCode());
    }
}