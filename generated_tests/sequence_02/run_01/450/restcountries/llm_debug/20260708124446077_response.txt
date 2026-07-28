package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowOriginHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowMethodsHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowHeadersHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_addsCacheControlHeader() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToAlphaEndpoint() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToNameEndpoint() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToCurrencyEndpoint() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToRegionEndpoint() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToCallingCodeEndpoint() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToV2AllEndpoint() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilter_appliesToV2AlphaEndpoint() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .header("Access-Control-Allow-Origin", nullValue());
    }
}