package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URI");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidNLowerThan2() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZeroReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanNPath() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, 5.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAxLessOrEqualDownwardRecurrence() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 10, 1e-10).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeXAndOddNSignFlip() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidXNonNumeric() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", "3", "abc").then().statusCode(400);
    }
}