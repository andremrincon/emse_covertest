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
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_iEquals5_returns200() {
        given().when().get("/api/pat/arrange-test-1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iLessThanMinus444_returns200() {
        given().when().get("/api/pat/arrange-test-2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "a");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iLessOrEqualMinus333_with_baab_returns200() {
        given().when().get("/api/pat/arrange-test-3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -333, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iGreaterThan666_returns200() {
        given().when().get("/api/pat/arrange-test-4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "zzzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_iEqualsMinus4_and_sAbab_returnsBodyZero() {
        given().when().get("/api/pat/arrange-test-5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        resp.then().statusCode(200).body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void test_iAtLeast555_and_sEqualsAbabba_returns200() {
        given().when().get("/api/pat/arrange-test-6").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 555, "ababba");
        resp.then().statusCode(200);
    }
}