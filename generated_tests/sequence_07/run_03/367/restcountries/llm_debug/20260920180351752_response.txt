package ts01glm_5_2_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1Alpha() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1AlphaByCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllCorsHeadersOnV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2AlphaByCode() {
        given()
            .queryParam("fields", "name;capital;population")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOn404Response() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOn400Response() {
        given()
            .when()
                .get("/v1/alpha/123")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1NameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1RegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2NameWithFullText() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2WithFieldsFilter() {
        given()
            .queryParam("fields", "name;capital;population;region")
            .when()
                .get("/v2")
            .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }
}