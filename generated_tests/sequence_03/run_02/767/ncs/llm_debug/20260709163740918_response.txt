package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE;
    static {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
            if (b == null || b.isEmpty()) b = "http://localhost:8080";
        }
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testGserConvergesReturns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/{a}/{x}", 5.5, 0.001);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPathReturns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/{a}/{x}", 5.5, 1000.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeXReturns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/{a}/{x}", 1.0, -1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGserWithXZeroReturns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/{a}/{x}", 2.5, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNonPositiveAReturns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/{a}/{x}", 0.0, 1.0);
        act.then().statusCode(400);
    }
}