package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testFilterInitAndDoFilterV1All() {
        int statusCode = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v1/all")
            .then()
                .extract()
                .statusCode();
        assertEquals(404, statusCode);
    }

    @Test(timeout = 60000)
    public void testFilterDoFilterV2Alpha() {
        int statusCode = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v2/alpha/US")
            .then()
                .extract()
                .statusCode();
        assertEquals(404, statusCode);
    }

    @Test(timeout = 60000)
    public void testCorsHeaderAccessControlAllowOrigin() {
        String header = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v1/all")
            .then()
                .extract()
                .header("Access-Control-Allow-Origin");
        assertNull(header);
    }

    @Test(timeout = 60000)
    public void testCorsHeaderAccessControlAllowMethods() {
        String header = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v1/all")
            .then()
                .extract()
                .header("Access-Control-Allow-Methods");
        assertNull(header);
    }

    @Test(timeout = 60000)
    public void testCorsHeaderAccessControlAllowHeaders() {
        String header = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v1/all")
            .then()
                .extract()
                .header("Access-Control-Allow-Headers");
        assertNull(header);
    }

    @Test(timeout = 60000)
    public void testCorsHeaderCacheControl() {
        String header = given()
                .baseUri(getBaseUrl())
            .when()
                .get("/v1/all")
            .then()
                .extract()
                .header("Cache-Control");
        assertNull(header);
    }
}