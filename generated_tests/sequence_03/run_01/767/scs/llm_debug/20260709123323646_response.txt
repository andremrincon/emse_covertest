package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatTooShort_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String txt = "text" + id;
        String pat = "ab";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatch_patlenGreaterThan2_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String txt = "xxxxxxxx" + id;
        String pat = "abc";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String txt = "xx" + pat + "yy" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnly_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String patrev = "cba";
        String txt = "xx" + patrev + "yy" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacent_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String patrev = "cba";
        String txt = "xx" + pat + "yy" + patrev + "zz" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromeAdjacent_patThenReverse_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String patrev = "cba";
        String txt = "xx" + pat + patrev + "zz" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseThenPatNonAdjacent_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String patrev = "cba";
        String txt = "xx" + patrev + "yy" + pat + "zz" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacent_returns200() {
        String id = UUID.randomUUID().toString().replace("-", "");
        String pat = "abc";
        String patrev = "cba";
        String txt = "xx" + patrev + pat + "zz" + id;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }
}