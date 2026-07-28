package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_too_short_returns_zero() {
        String uid = UUID.randomUUID().toString();
        String txt = "shorttxt" + uid;
        String pat = "ab";
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_pat_found_returns_one_when_no_reverse_present() {
        String uid = UUID.randomUUID().toString();
        String pat = "abc";
        String txt = "xx" + pat + "yy" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_reverse_found_returns_two_when_only_reverse_present() {
        String uid = UUID.randomUUID().toString();
        String pat = "abc";
        String rev = "cba";
        String txt = "start" + rev + "end" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_pat_followed_immediately_by_reverse_returns_index() {
        String uid = UUID.randomUUID().toString();
        String pat = "abc";
        String txt = "xx" + pat + "cba" + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_reverse_followed_immediately_by_pat_returns_index() {
        String uid = UUID.randomUUID().toString();
        String pat = "abc";
        String rev = "cba";
        String txt = "xx" + rev + pat + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_nonadjacent_returns_index_of_first_pat() {
        String uid = UUID.randomUUID().toString();
        String pat = "abc";
        String rev = "cba";
        String txt = "xx" + pat + "yy" + rev + uid;
        given().when().get("/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().body(equalTo("2"));
    }
}