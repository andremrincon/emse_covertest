package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeader() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeader() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeader() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(lessThan(300))
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(lessThan(300))
            .header("Cache-Control", nullValue());
    }
}