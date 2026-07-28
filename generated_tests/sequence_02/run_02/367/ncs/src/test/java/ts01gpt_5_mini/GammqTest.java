package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL_ALT");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_GserTypical() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.001);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GserZeroX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfLargeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Negative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.3);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidX_Negative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, -0.5);
        act.then().statusCode(400);
    }
}