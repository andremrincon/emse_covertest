package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
    public void testConstructorAndIEquals5Status200() {
        String u1 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", -500, "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -333, "abab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 700, "z").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "a").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 5, u1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeIndicesFinalBody10() {
        String u2 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 1, "example").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -1000, "abab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -500, u2);
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testLargeIStatus200() {
        String u3 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", -1, "test").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 555, "x").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 700, u3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMinus4FinalBody10() {
        String u4 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 0, "abab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", -4, u4);
        act.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testSEqualsBaabArrangeExecutedAndActStatus200() {
        String u5 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 2, "baab").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 3, "abab").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 1, u5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCompareToGreaterThanZeroArrangeAndActBody10() {
        String u6 = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 0, "zzzzzz").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 10, "ababba").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/{i}/{s}", 0, u6);
        act.then().body(equalTo("10"));
    }
}