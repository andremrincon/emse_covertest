package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherBothOddReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 5, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEvenReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOddReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 4, 5, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 4, 4, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidMReturns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", "abc", 5, 0.75).then().statusCode(400);
    }
}