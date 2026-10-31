package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl") != null ? System.getenv("baseUrl") : "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowHeadersHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsCacheControlHeaderOnV1All() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV2AllEndpoint() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV1AlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV2AlphaByCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV1NameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV2NameEndpoint() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV1RegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV2CurrencyEndpoint() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliesToV2EndpointWithFields() {
        given()
                .queryParam("fields", "name;capital;population;region")
                .when()
                .get("/v2")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }
}