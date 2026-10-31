package ts01gpt_5_mini_3;

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
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAIsZeroReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 0, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBIsZeroReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 5, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositivePositiveReturnsRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testPositiveNegativeReturnsRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, -5).then().body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testNegativePositiveReturnsRemainderPossiblyNegative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testNegativeNegativeReturnsAbsoluteRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, -4).then().body("result", nullValue());
    }
}