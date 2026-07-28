package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_shortPattern_returnsZero() {
        given().pathParam("txt", "arrange").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "some text";
        String pat = "ab";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_patternFound_returnsOne() {
        given().pathParam("txt", "arrange2").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "xxABCyy";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_reverseFound_returnsTwo() {
        given().pathParam("txt", "arrange3").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "zzCBAzz";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_bothNonAdjacent_returnsIndexOfFirst() {
        given().pathParam("txt", "arrange4").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "ABCxxCBA";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_palindromeAdjacent_patThenReverse_returnsIndex() {
        given().pathParam("txt", "arrange5").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "ABCCBA";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_palindromeAdjacent_reverseThenPat_returnsIndex() {
        given().pathParam("txt", "arrange6").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "CBAABC";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_noMatches_patternLong_returnsZero() {
        given().pathParam("txt", "arrange7").when().get("/api/pat/{txt}").then().statusCode(lessThan(300));
        String txt = "xxxxxxxxxx";
        String pat = "ABC";
        given().pathParam("txt", txt).pathParam("pat", pat).when().get("/api/pat/{txt}/{pat}").then().assertThat().body(equalTo("0"));
    }
}