package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            if (e2 == null || e2.isEmpty()) {
                env = "http://localhost:8080";
            } else {
                env = e2;
            }
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidNegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NIsZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XIsZero_NEquals2_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/2/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesBranch_XLessOrEqualOne_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        act.then().statusCode(200);
    }
}