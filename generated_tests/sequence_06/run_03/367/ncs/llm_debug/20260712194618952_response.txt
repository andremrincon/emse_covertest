package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("API_BASE_URL");
        String portStr = System.getenv("API_PORT");
        if (baseUrl == null || baseUrl.isEmpty()) baseUrl = "http://localhost";
        RestAssured.baseURI = baseUrl;
        try {
            RestAssured.port = portStr == null || portStr.isEmpty() ? 8080 : Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 3, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aOdd_bEven_returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aEven_bOdd_returns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 6, 5, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_edgeZeroX_returns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 0.0).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_nIsOne_returns200() {
        given().when().get("/api/remainder/{a}/{b}", 10, 4).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 1, 0.75);
        act.then().statusCode(200);
    }
}