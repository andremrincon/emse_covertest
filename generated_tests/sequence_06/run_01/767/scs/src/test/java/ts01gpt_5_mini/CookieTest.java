package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridProducesOneWhenValueLongAndStartsWithUser() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "UserId", "user1234", "example.com");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUseridProducesZeroWhenValueTooShort() {
        given().when().get("/api/calc/add/2/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1", "localhost");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSessionProducesOneForAmAndAbcCom() {
        given().when().get("/api/calc/add/3/4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSessionProducesTwoForOtherValues() {
        given().when().get("/api/calc/add/5/6").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUnknownNameProducesZero() {
        given().when().get("/api/calc/add/7/8").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/cookie/{name}/{val}/{site}", "someName", "anything", "example.com");
        assertEquals(200, act.getStatusCode());
    }
}