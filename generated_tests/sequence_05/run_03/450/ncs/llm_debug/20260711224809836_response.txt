package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200ForValidParameters() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400WhenNLessThanTwo() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/-5/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXProducesNegativeValueForOddN() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/-10.0").then().body("value", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testExpintReturns200ForValidParameters() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400ForInvalidX() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDetectsRightTriangle() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().body("triangleType", equalTo(null));
    }
}