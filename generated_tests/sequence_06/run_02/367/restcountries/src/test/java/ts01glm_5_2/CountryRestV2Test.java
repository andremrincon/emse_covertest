package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_validCodeNoFields_returns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_shortCodesReturns400() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_validCodesNoFields_returns200() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_unknownCodesReturns404() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLengthReturns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validCurrencyReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_unknownCurrencyReturns404() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_validNameReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_validCodeReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_validCapitalReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_validRegionReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(200);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void getBySubRegion_validSubRegionReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/subregion/{subregion}", "Western Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_validLanguageReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByDemonym_validDemonymReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_validBlocReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }
}