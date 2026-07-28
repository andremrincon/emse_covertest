package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1Alpha() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1Name() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Capital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(lessThan(300))
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV1Region() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV1CallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV2Name() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV2Region() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV2Capital() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(lessThan(300))
                .header("Access-Control-Allow-Origin", nullValue());
    }
}