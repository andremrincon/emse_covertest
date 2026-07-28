package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            String prop = System.getProperty("baseUrl");
            RestAssured.baseURI = (prop != null && !prop.isEmpty()) ? prop : "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_positive_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 5.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_negative_signFlip_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -5.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_elseBranch_smallX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 50, 1.0e-10);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_bessj0_largeAx_branch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 9.0);
        assertEquals(200, resp.getStatusCode());
    }
}