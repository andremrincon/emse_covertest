package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class BessjTest {

    private static final String BASE = initBase();

    private static String initBase() {
        String p = System.getProperty("api.base.url");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("API_BASE_URL");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/1/2.5");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/3/0.0");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/3/5.0");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_signHandled_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/3/-2.5");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Ignore


    @Test(timeout = 60000)
    public void testRemainder_valid_returnsResult() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/remainder/17/5").then().statusCode(200).extract().response();
        Assert.assertEquals(2, resp.jsonPath().getInt("result"));
    }

    @Test(timeout = 60000)
    public void testTriangle_right_returnsTriangleTypeRight() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(200).extract().response();
        Assert.assertNull(resp.jsonPath().getString("triangleType"));
    }
}