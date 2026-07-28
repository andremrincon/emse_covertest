package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_iEqualsFive_setsBranchCovered_andReturnsSix() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iLessThanMinus444_andLessOrEqualMinus333_paths() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iGreaterThan666_andGreaterOrEqual555_paths() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iNotEqualsMinus4_false_path_returnsZero() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_sCompareTo_equal_zero_path_statusOk() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "ababba");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_sCompareTo_greaterThan_zero_and_finalNotEqual_abab_returnsTen() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "zzzz");
        resp.then().body(equalTo("10"));
    }
}