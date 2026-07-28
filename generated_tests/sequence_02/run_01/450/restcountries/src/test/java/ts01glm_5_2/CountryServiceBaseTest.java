package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaTwoCharCodeNotFound() {
        given()
        .when()
            .get("/v1/alpha/XX")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaThreeCharCodeNotFound() {
        given()
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValidTwoCharCode() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValidThreeCharCode() {
        given()
        .when()
            .get("/v1/alpha/USA")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListValidCodes() {
        given()
            .queryParam("codes", "US,CA,MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNotFoundCodes() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMixedCodes() {
        given()
            .queryParam("codes", "US,XX,CA")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactNameMatch() {
        given()
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingMatch() {
        String name = "French Republic";
        String encoded;
        try {
            encoded = java.net.URLEncoder.encode(name, "UTF-8").replace("+", "%20");
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        given()
            .pathParam("name", encoded)
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNoMatch() {
        given()
            .queryParam("fullText", "true")
        .when()
            .get("/v1/name/NonExistentCountry")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJsonViaV1All() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMissingCodesParam() {
        given()
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }
}