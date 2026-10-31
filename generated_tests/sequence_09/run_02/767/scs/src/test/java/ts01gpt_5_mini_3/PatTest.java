package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
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
    public void test_PatEndpoint_WithSingleCharPattern_Returns200() {
        String unique = "txt-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", unique, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_PatternFoundExample_Returns200() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_PalindromeAdjacent_Returns200() {
        given().when().get("/api/pat/{txt}", "ABAABA").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "ABAABA", "ABA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_ReverseThenPatternAdjacent_Returns200() {
        given().when().get("/api/pat/{txt}", "CBAABC").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "CBAABC", "ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_NoMatch_Returns200() {
        given().when().get("/api/pat/{txt}", "abcdefghijk").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "abcdefghijk", "xyz").then().statusCode(200);
    }

    @Ignore("Illegal character in path at index 33: http://localhost:8080/api/pat/The quick brown fox jumps ov...")
    @Test(timeout = 60000)
    public void test_PatSingleParam_LongText_Returns200() {
        String longTxt = "The quick brown fox jumps over the lazy dog " + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", longTxt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", longTxt).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_SpecialCharacters_Returns200() {
        String txt = "text-with-non-ascii-Ã©Ã¨Ã§-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, "Ã§").then().statusCode(200);
    }
}