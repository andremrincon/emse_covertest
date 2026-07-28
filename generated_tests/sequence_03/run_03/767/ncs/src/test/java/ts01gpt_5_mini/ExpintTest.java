package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

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
    public void testExpint_continuedFractionPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_seriesPath_nm1Zero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/1/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nTwo_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/2/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/-1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/-5/2.5").then().statusCode(400);
    }
}