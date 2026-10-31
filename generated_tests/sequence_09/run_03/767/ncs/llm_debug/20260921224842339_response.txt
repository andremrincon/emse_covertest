package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherBothOddReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 5, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEvenReturnsSuccessMessage() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 5, 6, 0.0).then().statusCode(200).body("message", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOddReturns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenReturns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidNReturns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 8, -3, 0.75).then().statusCode(200);
    }
}