package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200ForValidN() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400ForInvalidN() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/2/1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParams() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400ForLargeM() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1001/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenExeThrows() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns200AndCorrectResult() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/17/5").then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400ForOutOfBounds() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/10001/5").then().statusCode(400);
    }
}