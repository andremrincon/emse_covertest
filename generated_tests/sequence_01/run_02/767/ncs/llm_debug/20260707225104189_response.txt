package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isEmpty()) url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testGammq_GserPath_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "0.001").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfPath_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_XZero_UsesGserZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonPositiveA_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "0.0", "2.3").then().statusCode(400);
    }
}