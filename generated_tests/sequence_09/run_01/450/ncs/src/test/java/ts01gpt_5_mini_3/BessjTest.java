package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) {
            String env = System.getenv("BASE_URL");
            RestAssured.baseURI = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        } else {
            RestAssured.baseURI = url;
        }
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 5.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axLessOrEqualN_backwardRecurrence_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 6, 2.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_signFlip_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_lessThanTwo_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        assertEquals(400, resp.getStatusCode());
    }
}