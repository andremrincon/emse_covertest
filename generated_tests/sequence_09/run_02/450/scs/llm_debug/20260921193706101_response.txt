package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void test_shortPattern_returnsZero() {
        String txt = "sampleText-" + UUID.randomUUID().toString();
        String pat = "a";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_patternFound_returnsOne() {
        String txt = "xxxpatternyyy-" + UUID.randomUUID().toString();
        String pat = "pattern";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_reverseOnly_returnsTwo() {
        String pat = "abcde";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "prefix-" + patrev + "-suffix-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_patFollowedByReverse_adjacent_returnsIndexZero() {
        String pat = "abcd";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = pat + patrev + "-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_reverseFollowedByPat_adjacent_returnsIndexZero() {
        String pat = "abcd";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = patrev + pat + "-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_patAndReverse_nonAdjacent_returnsIndexZero() {
        String pat = "abcd";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = pat + "xx" + patrev + "-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", txt, pat).then().body(equalTo("0"));
    }
}