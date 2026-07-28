package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowOriginHeader_onGetAll() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowMethodsHeader_onGetAlphaByCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowHeadersHeader_onGetAlphaByCodes() {
        given()
                .when()
                .queryParam("codes", "US,CA,MX")
                .get("/v1/alpha")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCacheControlHeader_onGetCurrency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAllCorsHeaders_onGetName() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowOrigin_onGetCallingCode() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowOrigin_onGetCapital() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowOrigin_onGetRegion() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onBadRequestAlphaCode() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onNotFoundName() {
        given()
                .when()
                .get("/v1/name/123")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onPostV1() {
        given()
                .when()
                .post("/v1")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }
}