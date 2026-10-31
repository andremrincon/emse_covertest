package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnZero_when_i5_and_s_a() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "a").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReturnThree_when_i28_and_s_abc() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "abc").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testI1_branch_executed_when_i7_returns_three() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "zzz").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testReturnTwo_when_i0_and_s_zulu() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 0, "zulu").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testInvalidIntegerParam_returns_400() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", "2147483648", "valid-string").then().statusCode(400);
    }
}