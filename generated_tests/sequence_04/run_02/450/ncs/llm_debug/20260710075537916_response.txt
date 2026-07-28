package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/-5/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjZeroXReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNUsesAsymptoticBranch() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualNUsesBackwardRecurrence() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/10/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNSignFlip() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/-2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjVerySmallXHandlesUnderflowBranches() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/1e-10").then().statusCode(200);
    }
}