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
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_PatShortPattern_returnsZero() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-"+uniq).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xx"+uniq+"yy", "ab");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_PatFound_returnsOne() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "seed-"+uniq).then().statusCode(lessThan(300));
        String txt = "xx" + uniq + "abczz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, "abc");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_ReverseFound_returnsTwo() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "seed2-"+uniq).then().statusCode(lessThan(300));
        String txt = "xx" + uniq + "cbazz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, "abc");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_PatAndReverseNonAdjacent_returnsIndex() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "seed3-"+uniq).then().statusCode(lessThan(300));
        String txt = "xx" + uniq + "abcyycbazz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, "abc");
        resp.then().body(equalTo("" + ("xx"+uniq).length()));
    }

    @Test(timeout = 60000)
    public void test_PatAndReverseAdjacent_returnsIndex() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "seed4-"+uniq).then().statusCode(lessThan(300));
        String txt = "xx" + uniq + "abccbaz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, "abc");
        resp.then().body(equalTo("" + ("xx"+uniq).length()));
    }

    @Test(timeout = 60000)
    public void test_PatEndpoint_basicStatus200() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "pre-"+uniq).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "The quick brown fox "+uniq);
        resp.then().statusCode(200);
    }
}