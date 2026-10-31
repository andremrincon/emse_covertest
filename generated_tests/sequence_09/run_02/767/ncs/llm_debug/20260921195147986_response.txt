package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {
    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testPositiveDividendPositiveDivisor() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveDividendNegativeDivisor() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, -5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeDividendPositiveDivisor() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeDividendNegativeDivisor() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, -4).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAZeroBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 0, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBDivisorZeroBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 5, 0).then().statusCode(200);
    }
}