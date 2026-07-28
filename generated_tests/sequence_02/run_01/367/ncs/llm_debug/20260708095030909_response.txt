package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TriangleClassificationTest {

    private static final String BASE;
    static {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testTriangleWithNonPositiveSideReturns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/19/4").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/23/6").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/29/7").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get(BASE + "/api/remainder/31/8").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/{a}/{b}/{c}", 3, 4, 6);
        assertEquals(200, resp.getStatusCode());
    }
}