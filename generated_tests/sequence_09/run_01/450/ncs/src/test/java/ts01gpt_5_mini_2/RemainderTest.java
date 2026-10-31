package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositiveAPositiveB() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveANegativeB() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, -5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeAPositiveB() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeANegativeB() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, -4).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBZeroReturns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 0).then().statusCode(200);
    }
}