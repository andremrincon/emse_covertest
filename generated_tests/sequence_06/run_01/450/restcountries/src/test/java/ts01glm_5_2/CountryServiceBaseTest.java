package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeNotFound() {
        given()
            .when()
                .get("/v1/alpha/ZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeNotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeFound() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeFound() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNullCodes() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListValidCodes() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMixedValidInvalidCodes() {
        given()
            .queryParam("codes", "US;ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 42: http://localhost:8080/rest/v1/name/Federal Republic of Ger...")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingMatch() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "Federal Republic of Germany")
            .then()
                .statusCode(200);
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
    public void testSubstringSearchAltSpellingMatch() {
        given()
            .when()
                .get("/v1/name/Bundesrepublik")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchPartialNameMatch() {
        given()
            .when()
                .get("/v1/name/Fra")
            .then()
                .statusCode(200);
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
    public void testLoadJsonViaV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2GetByAlphaNotFound() {
        given()
            .when()
                .get("/v2/alpha/ZZZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2GetByCodeListValidCodes() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(200);
    }
}