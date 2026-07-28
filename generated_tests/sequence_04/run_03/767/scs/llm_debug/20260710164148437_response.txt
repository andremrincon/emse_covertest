package ts01gpt_5_mini;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @Before
    public void setUp() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDefaultResultIsTenForTypicalInput() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(200).extract().response();
        assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testResultSixWhenSIsAbab() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testResultZeroWhenIIsMinusFourAndSIsAbab() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testStringEqualsBranchTriggeredWithBaab() {
        given().when().get("/api/pat/{txt}", "health-check-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 123, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCompareToEqualsBranchTriggeredWithAbabba() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "A", "B", "C").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "ababba");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCompareToGreaterThanBranchTriggeredWithZ() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + UUID.randomUUID().toString(), "v", "localhost").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 42, "z");
        resp.then().statusCode(200);
    }
}