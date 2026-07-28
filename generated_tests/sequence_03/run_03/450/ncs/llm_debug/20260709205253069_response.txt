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
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 3, 0.75);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 4, 3, 0.75);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 4, 2, 0.5);
        assertEquals(200, resp.getStatusCode());
    }
}