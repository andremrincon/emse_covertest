package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void configure() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesUrl() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}", "http://abc/def");
        org.junit.Assert.assertEquals(400, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesDate() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}", "mon01jan");
        org.junit.Assert.assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointRecognizesFloatingPointWithExponent() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}", "12.34e+56");
        org.junit.Assert.assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpointReturnsNoneForNonMatchingText() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}", "xyz");
        org.junit.Assert.assertEquals(200, r.getStatusCode());
    }
}