package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null) base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nOdd_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/11/5/0.75");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/11/6/0.75");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/10/5/0.5");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nEven_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/10/6/0.75");
        assertEquals(200, res.getStatusCode());
    }
}