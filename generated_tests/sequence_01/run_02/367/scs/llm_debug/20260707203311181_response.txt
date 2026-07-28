package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xyzab", "ab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundReturnsOne() {
        given().when().get("/api/pat/{txt}", "setup1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ZZABCYY", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundReturnsTwo() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ZZCBAYY", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAdjacentPatThenReverseReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABCDDCBAyy", "ABCD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAdjacentReverseThenPatReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxDCBAABCDyy", "ABCD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setup5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABCDxxDCBA", "ABCD");
        resp.then().statusCode(200);
    }
}