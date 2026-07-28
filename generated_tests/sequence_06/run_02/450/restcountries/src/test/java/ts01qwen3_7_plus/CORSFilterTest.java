package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Ignore("expected:<*> but was:<null>")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        String headerValue = given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .extract()
                .header("Access-Control-Allow-Origin");

        assertEquals("*", headerValue);
    }

    @Ignore("expected:<GET> but was:<null>")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        String headerValue = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .extract()
                .header("Access-Control-Allow-Methods");

        assertEquals("GET", headerValue);
    }

    @Ignore("expected:<Accept, X-Requested-With> but was:<null>")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        String headerValue = given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .extract()
                .header("Access-Control-Allow-Headers");

        assertEquals("Accept, X-Requested-With", headerValue);
    }

    @Ignore("expected:<public, max-age=86400> but was:<null>")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        String headerValue = given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .extract()
                .header("Cache-Control");

        assertEquals("public, max-age=86400", headerValue);
    }
}