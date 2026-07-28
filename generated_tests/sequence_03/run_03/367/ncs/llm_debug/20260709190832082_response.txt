package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasKey;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gser_zeroX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_largeX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalidA_zero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "0.0", "2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_exampleResponseContainsResultKey() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3").then().body("$", hasKey("resultAsDouble"));
    }

    @Test(timeout = 60000)
    public void testGammq_nonNumericX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "abc").then().statusCode(400);
    }
}