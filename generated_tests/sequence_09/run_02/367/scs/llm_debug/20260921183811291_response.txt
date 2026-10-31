package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDefaultResultZero_whenIIsZeroAndSIsLessThanHello() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 0, "a").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCompareToLess_setsResultTwo_whenIIsFiveAndSIsGreaterThanHello() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "z").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testYGreater_setsResultThree_whenIIsSix() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 6, "a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testXPlusYEquals56_branchExecuted_whenIIs28() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testXsConcatYEqualsHello7_branchExecuted_whenIIs7() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "a").then().body(equalTo("3"));
    }
}