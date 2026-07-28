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
    public void doFilterAddsAccessControlAllowOriginHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowMethodsHeaderOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAccessControlAllowHeadersHeaderOnV1Alpha() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCacheControlHeaderOnV1Name() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsAllCORSHeadersOnV1Capital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Region() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1CallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2Name() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void doFilterAddsCORSHeadersOnV2Region() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }
}