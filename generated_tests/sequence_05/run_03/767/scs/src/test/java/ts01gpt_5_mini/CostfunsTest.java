package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    private static final String BASE = System.getProperty("baseUrl", System.getenv("BASE_URL")) == null ? "http://localhost:8080" : (System.getProperty("baseUrl", System.getenv("BASE_URL")));

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testCostfuns_allStringGreater() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_negativeLarge() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -500, "abab").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_bigPositive() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "1", "example.com").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 1000, "aaaa").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_minusFour_zeroResult() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(200).assertThat().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToEqualBranch() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/costfuns/{i}/{s}", 0, "ababba").then().statusCode(200).assertThat().body(equalTo("10"));
    }
}