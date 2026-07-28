package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        RestAssured.baseURI = prop != null ? prop : (env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGserConvergesForSmallX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.001);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfUsedForLargeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserHandlesZeroX() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.0, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserThrowsForNegativeXProducesBadRequest() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.0, -0.1);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeRejectsNonPositiveAProducesBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 0.0, 1.0);
        act.then().statusCode(400);
    }
}