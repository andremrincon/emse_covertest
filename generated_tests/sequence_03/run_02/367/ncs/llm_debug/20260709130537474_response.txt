package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("base.url");
        if (env == null || env.isEmpty()) {
            String e = System.getenv("BASE_URL");
            RestAssured.baseURI = (e != null && !e.isEmpty()) ? e : "http://localhost:8080";
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_success() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_Series_success() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_case() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_case() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_invalid_negativeN_badRequest() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_invalid_negativeX_badRequest() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, -0.1);
        resp.then().statusCode(400);
    }
}