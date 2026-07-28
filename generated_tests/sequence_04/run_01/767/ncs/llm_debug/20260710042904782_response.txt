package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testGammq_gser_smallX_returns200() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/5.5/0.001");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_largeX_returns200() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_xZero_returns200() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/5.5/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_negativeX_returns400() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/5.5/-1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_nonPositiveA_returns400() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/-1.0/2.3");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_boundary_xEqualsAPlusOne_goesToGcf_returns200() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        double a = 5.5;
        double x = a + 1.0;
        Response resp = given().baseUri(base()).when().get("/api/gammq/" + a + "/" + x);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalid_nonNumericA_returns400() {
        given().baseUri(base()).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base()).when().get("/api/gammq/abc/2.3");
        resp.then().statusCode(400);
    }
}