package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowOriginHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowMethodsHeader() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsAccessControlAllowHeadersHeader() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAddsCacheControlHeader() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2AllEndpoint() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2AlphaEndpoint() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2NameEndpoint() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2CapitalEndpoint() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2RegionEndpoint() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2SubregionEndpoint() {
        given()
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2LangEndpoint() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedToV2RegionalblocEndpoint() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }
}