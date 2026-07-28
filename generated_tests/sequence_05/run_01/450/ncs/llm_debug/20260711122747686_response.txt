package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testZeroOrNegativeSides_returns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral_returns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle_returns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 0.1).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene_returns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}