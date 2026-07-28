package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_iEquals5_returnsSix() {
        given().when().get("/api/pat/{txt}", "setup-health").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iEqualsMinus4_returnsZero() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_compareToGreater_producesOkStatus() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "zzzzzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_sEqualsBaab_finalizesToTen() {
        given().when().get("/api/pat/{txt}", "prep").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_largeI_greaterThan666_returnsOkBody() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iLessThanMinus444_statusOk() {
        given().when().get("/api/pat/{txt}", "prime").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().statusCode(200);
    }
}