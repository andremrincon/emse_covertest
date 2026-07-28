package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoLetterCode_returnsCountry() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeLetterCode_returnsCountry() {
        given()
                .when()
                .get("/v1/alpha/USA")
                .then()
                .statusCode(200)
                .body("alpha3Code", equalTo("USA"));
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFoundCode_returns404() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidCode_returns400() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_multipleCodes_returnsCountries() {
        given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Test(timeout = 60000)
    public void getByCodeList_codesNotFound_returns404() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_invalidCodes_returns400() {
        given()
                .queryParam("codes", "123")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returnsCountry() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("France"));
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returnsCountry() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/United%20States%20of%20America")
                .then()
                .statusCode(200)
                .body("[0].alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void fulltextSearch_nameNotFound_returns404() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_allEndpoint_triggersJsonLoad() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void loadJson_v2AllEndpoint_triggersJsonLoad() {
        given()
                .queryParam("fields", "name;capital;region;population;flag")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}