package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testPatPatternFound_ABAB() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatSingleChar_patA_banana() {
        given().when().get("/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "banana", "a");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatTxtEndpointSimple() {
        given().when().get("/api/pat/{txt}/{pat}", "banana", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatReverseFirst() {
        given().when().get("/api/pat/{txt}", "BABAABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "BABAABAB", "ABAB");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatPalindromeContiguous() {
        given().when().get("/api/pat/{txt}", "ABABBABA").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABBABA", "ABAB");
        assertEquals(200, resp.getStatusCode());
    }
}