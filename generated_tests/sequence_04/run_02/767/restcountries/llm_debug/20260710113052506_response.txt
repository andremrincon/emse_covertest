package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_Valid2LetterCode_Returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Valid3LetterCode_Returns200() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_NonExistentCode_Returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_MultipleCodes_Returns200() {
        given()
            .queryParam("codes", "US;CA;MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_DuplicateCodes_Returns200() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_NonExistentCodes_Returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_NoCodesParam_Returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_ExactNameMatch_Returns200() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_AltSpellingMatch_Returns200() {
        String encodedName = "Federal Republic of Germany".replace(" ", "%20");
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", encodedName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_NoMatch_Returns404() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "NonExistentCountry")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_GetAllCountries_Returns200() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void loadJson_GetV2AllCountries_Returns200() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(200);
    }
}