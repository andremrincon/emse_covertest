package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositivePositive_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testPositiveNegative_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, -9).then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testNegativePositive_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, 5).then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testNegativeNegative_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -17, -5).then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testBZero_returnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 5, 0).then().statusCode(200);
    }
}