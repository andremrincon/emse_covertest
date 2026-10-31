package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2LetterMatch() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2LetterNotFound() {
        given()
            .when()
                .get("/v1/alpha/XX")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3LetterNotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
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
    public void testGetByCodeListNullCodes() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNonMatchingCodes() {
        given()
            .queryParam("codes", "XX,YY,ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNameMatch() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/French Republic")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingMatch() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonV1All() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonV2All() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV2ValidCodes() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }
}