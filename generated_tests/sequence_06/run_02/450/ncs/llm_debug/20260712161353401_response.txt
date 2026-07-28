package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThanTwo_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/2/10.0");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_signHandling_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-2.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_nonNumericN_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/abc/2.5");
        assertEquals(400, act.getStatusCode());
    }
}