package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_GserPath_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfPath_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        when().get("/api/gammq/{a}/{x}", 0.001, 1000.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_XZero_GserSpecial_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        when().get("/api/gammq/{a}/{x}", 1.0, 0.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        when().get("/api/gammq/{a}/{x}", 5.5, -1.0).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonPositiveA_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        when().get("/api/gammq/{a}/{x}", -1.0, 2.3).then().statusCode(400);
    }
}