package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeader() {
        Response response = given()
                .baseUri("http://localhost:8080/rest")
                .when()
                .get("/v1/all");

        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeader() {
        Response response = given()
                .baseUri("http://localhost:8080/rest")
                .when()
                .get("/v1/alpha/US");

        assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeader() {
        Response response = given()
                .baseUri("http://localhost:8080/rest")
                .when()
                .get("/v1/name/France");

        assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlHeader() {
        Response response = given()
                .baseUri("http://localhost:8080/rest")
                .when()
                .get("/v1/capital/London");

        assertNull(response.getHeader("Cache-Control"));
    }
}