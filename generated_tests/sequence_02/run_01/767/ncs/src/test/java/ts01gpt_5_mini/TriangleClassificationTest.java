package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base.url", System.getenv("API_BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleWithNonPositiveSideReturnsOkStatus() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/{a}/{b}", 18, 4).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNonTriangleInequalityReturnsOkStatus() {
        given().when().get("/api/remainder/{a}/{b}", 19, 6).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 2);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/{a}/{b}", 20, 7).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 7, 7, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/{a}/{b}", 21, 8).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        assertEquals(200, resp.getStatusCode());
    }
}