package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnAlphaCodeEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOn404Response() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOn400Response() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCallingCodeEndpoint() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }
}