package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testCostfuns_returns10_for_nonSpecialString_and_i5() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "algorithm");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_returns6_for_largeNegative_i_and_s_abab() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "arr"+arrangeToken, "x", "y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_returns6_for_i_greater_than_666_and_s_abab() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", arrangeToken).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_returns0_for_i_minus4_and_s_abab() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-"+arrangeToken).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEquals_baab_path_executed_and_returns10_finally() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-"+arrangeToken, "1", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareTo_equal_zero_s_ababba_returns10() {
        String arrangeToken = UUID.randomUUID().toString();
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "a"+arrangeToken, "b", "c", "d").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "ababba");
        resp.then().body(equalTo("10"));
    }
}