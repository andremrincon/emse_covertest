package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class ExpintTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null) base = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", -1, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NEquals0_XPositive_ReturnsResultField() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", 0, 2.5).then().statusCode(200).body("$", notNullValue());
    }

    @Test(timeout = 60000)
    public void testExpint_NGreaterThan0_XEquals0_Returns200() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", 3, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThan1_ContinuedFraction_ReturnsResult() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200).body("$", notNullValue());
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_NEquals1_Returns200() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidXString_Returns400() {
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/expint/{n}/{x}", 3, "True").then().statusCode(400);
    }
}