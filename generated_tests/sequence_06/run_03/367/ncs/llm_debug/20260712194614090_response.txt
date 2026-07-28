package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleRight_returns200() {
        when().
            get("/api/triangle/3/4/5").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_success_returns200_and_arrange_remainder_and_gammq() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        when().
            get("/api/bessj/3/2.5").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_success_returns200() {
        when().
            get("/api/expint/3/2.5").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_success_returns200() {
        when().
            get("/api/fisher/10/5/0.75").
        then().
            statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mGreaterThanLimit_returns400() {
        when().
            get("/api/fisher/1001/5/0.5").
        then().
            statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_outOfBounds_returns400() {
        when().
            get("/api/remainder/100000/5").
        then().
            statusCode(400);
    }
}