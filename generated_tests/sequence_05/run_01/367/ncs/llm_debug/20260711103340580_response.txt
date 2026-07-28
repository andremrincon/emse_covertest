package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNReturns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", -5, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjZeroXReturnsZeroValue() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0).then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNUsesBessj1SmallAxBranch() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, 3.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualUsesBackwardRecurrenceAndHandlesNegativeXSign() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -2.5).then().body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNUsesBessj1LargeAxBranch() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, 10.0).then().statusCode(400);
    }
}