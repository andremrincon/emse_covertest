package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidSides_NonPositiveReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 0, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 10, 3).then().statusCode(lessThan(300));
        int v = 3 + (int)(UUID.randomUUID().toString().hashCode() & 0x3);
        given().when().get("/api/triangle/{a}/{b}/{c}", v, v, v).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxGreaterOrEqualSumReturns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 2).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}