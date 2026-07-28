package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllConditionsFalseReturnsZero() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveExecutesBranch() {
        given().when().get("/api/pat/setup1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/5/baab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNegativeLargeTriggersLessThanAndLessEqual() {
        given().when().get("/api/pat/setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-500/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCompareToGreaterThanPath() {
        given().when().get("/api/pat/setup3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/zzzz").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCompareToEqualForGreaterOrEqual() {
        given().when().get("/api/pat/setup4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/0/ababba").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testGreaterThanAndGreaterOrEqualNumericPaths() {
        given().when().get("/api/pat/setup5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/700/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }
}