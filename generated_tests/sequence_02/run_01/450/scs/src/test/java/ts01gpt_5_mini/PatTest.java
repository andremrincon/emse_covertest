package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
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
    public void testPatNotFoundReturnsOk() {
        given().when().get("/api/pat/{txt}", "abcdefghijk").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "abcdefghijk", "xyz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundOnlyReturnsOk() {
        given().when().get("/api/pat/{txt}", "xxbananayy").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxbananayy", "banana").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyReturnsOk() {
        given().when().get("/api/pat/{txt}", "xxedcbayy").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxedcbayy", "abcde").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothFoundNonAdjacentReturnsOk() {
        given().when().get("/api/pat/{txt}", "00ABAB11BABA99").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "00ABAB11BABA99", "ABAB").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBothFoundAdjacentPalindromeReturnsOk() {
        given().when().get("/api/pat/{txt}", "xxABCCBAYY").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xxABCCBAYY", "ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testShortPatternDoesNotSearchReturnsOk() {
        given().when().get("/api/pat/{txt}", "anytext").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "anytext", "ab").then().statusCode(200);
    }
}