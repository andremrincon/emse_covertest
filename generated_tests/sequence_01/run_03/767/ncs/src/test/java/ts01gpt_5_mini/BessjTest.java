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
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_usesAsymptoticForms() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axLessThanOrEqualN_usesBackwardRecurrence() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 5, 1.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returnsOk() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0.0);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_andOddN_signHandled() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_lessThanTwo_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_nonNumeric_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", "abc", 2.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_bessj0AndBessj1_smallAxBranch() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 5.0);
        assertEquals(200, resp.getStatusCode());
    }
}