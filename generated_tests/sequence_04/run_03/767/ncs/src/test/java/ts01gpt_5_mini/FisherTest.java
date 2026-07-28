package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FisherTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_OddN_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/1/1/0.75");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/3/2/0.0");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_OddN_Returns200() {
        given().when().get("/api/remainder/10/4").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/2/1/0.75");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_EvenN_Returns200() {
        given().when().get("/api/triangle/6/8/10").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/10/6/0.75");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_Format_Returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/fisher/abc/5/0.75");
        assertEquals(400, res.getStatusCode());
    }
}