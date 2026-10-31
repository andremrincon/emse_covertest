package ts01glm_5_2_3;

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
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            String envUrl = System.getenv("BASE_URL");
            if (envUrl != null && !envUrl.isEmpty()) {
                RestAssured.baseURI = envUrl;
            } else {
                RestAssured.baseURI = "http://localhost:8080/rest";
            }
        }
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue())
                .header("Access-Control-Allow-Headers", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1AlphaByCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1AlphaCodes() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Name() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1CallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Capital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV1Region() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2All() {
        given()
            .queryParam("fields", "name;capital;region;population;flag")
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Name() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnV2Regionalbloc() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", nullValue())
                .header("Cache-Control", nullValue());
    }
}