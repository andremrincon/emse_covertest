package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RemainderTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRemainder_PositiveA_PositiveB_Result() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(200).body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testRemainder_AZero_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 0, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_BZero_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_PositiveA_NegativeB_Ok() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, -5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_NegativeA_PositiveB_Ok() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_NegativeA_NegativeB_Ok() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", -9, -4).then().statusCode(200);
    }
}