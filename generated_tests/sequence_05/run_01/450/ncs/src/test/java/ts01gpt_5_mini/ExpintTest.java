package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String base() {
        String b = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        return b != null ? b : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFractionPath_returns200() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/3/2.5");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_seriesWithPsiPath_returns200() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/4/5/6").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/3/0.1");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_nZeroBranch_returns200() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/5/12/13").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/0/1.0");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_xZeroNm1NonZero_returns200() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/6/8/10").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/2/0");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_returns400() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/-1/1");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_xZeroWithNZero_returns400() {
        String base = base();
        given().baseUri(base).when().get("/api/triangle/7/24/25").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/expint/0/0");
        Assert.assertEquals(400, resp.getStatusCode());
    }
}