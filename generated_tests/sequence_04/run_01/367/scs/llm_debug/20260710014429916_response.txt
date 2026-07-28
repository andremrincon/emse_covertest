package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

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
    public void test_shortPattern_returnsZero_status200() {
        String uid = UUID.randomUUID().toString();
        String txt = "sampletext-" + uid;
        String pat = "ab";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_patternFound_only_status200() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String txt = "xx" + pat + "yy-" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverseFound_only_status200() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "zz" + patrev + "zz-" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_patAndReverse_nonAdjacent_status200() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "XX" + pat + "YY" + patrev + "ZZ-" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_palindrome_patThenReverse_adjacent_status200() {
        String uid = UUID.randomUUID().toString();
        String pat = "ABCD";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "xx" + pat + patrev + "yy-" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_palindrome_reverseThenPat_adjacent_status200() {
        String uid = UUID.randomUUID().toString();
        String pat = "WXYZ";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "start-" + patrev + pat + "-end-" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().statusCode(200);
    }
}