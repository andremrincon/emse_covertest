package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasKey;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("API_BASE", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGserZeroX_hasResultKey() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0).then().body("$", hasKey("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testGcfLargeX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNegativeX_returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", 5.5, -1.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidANegative_returns400() {
        given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", -1.0, 2.0).then().statusCode(400);
    }
}