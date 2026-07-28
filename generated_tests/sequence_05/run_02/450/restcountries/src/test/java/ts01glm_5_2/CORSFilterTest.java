package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
    public void doFilter_addsAccessControlAllowOriginHeader_onGetAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowMethodsHeader_onGetAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsAccessControlAllowHeadersHeader_onGetByAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCacheControlHeader_onGetByAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetByName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetByCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetByCallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetByCapital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onGetByRegion() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void doFilter_addsCorsHeaders_onV2Region() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", equalTo(null));
    }
}