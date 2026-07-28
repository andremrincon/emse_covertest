package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
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
    public void test_PatLengthTwo_ReturnsZero() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "hello world";
        String pat = "ab";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_PatFoundOnly_ReturnsOne() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "xxABCyy";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_ReverseFoundOnly_ReturnsTwo() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "xxCBAyy";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_PatThenReverse_NonAdjacent_ReturnsIndex() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "XYZABC123CBA";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_PatThenReverse_Contiguous_ReturnsIndex() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "XXXABCCBAYYY";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_ReverseThenPat_Contiguous_ReturnsIndex() {
        String healthTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthTxt).then().statusCode(lessThan(300));
        String txt = "ZZZCBAABCxxx";
        String pat = "ABC";
        Response act = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        act.then().assertThat().body(equalTo("3"));
    }
}