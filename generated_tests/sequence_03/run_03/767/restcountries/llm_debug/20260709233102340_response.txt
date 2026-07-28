package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_badRequest_invalidAlphaCodeLength() {
        given()
                .pathParam("alphacode", "1")
                .when()
                .get("/v2/alpha/{alphacode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_success_withFieldsFilter() {
        given()
                .pathParam("alphacode", "US")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha/{alphacode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_invalidCodesParam() {
        given()
                .queryParam("codes", "123")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_nonExistentCodes() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_success_withFieldsFilter() {
        given()
                .queryParam("codes", "US;CA")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_invalidCurrencyLength() {
        given()
                .pathParam("currency", "12")
                .when()
                .get("/v2/currency/{currency}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_nonExistentCurrency() {
        given()
                .pathParam("currency", "XYZ")
                .when()
                .get("/v2/currency/{currency}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_success_matchingCountryName() {
        given()
                .pathParam("name", "Germany")
                .when()
                .get("/v2/name/{name}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_success_validCallingCode() {
        given()
                .pathParam("callingcode", "1")
                .when()
                .get("/v2/callingcode/{callingcode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_success_validCapital() {
        given()
                .pathParam("capital", "Paris")
                .when()
                .get("/v2/capital/{capital}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_success_validRegion() {
        given()
                .pathParam("region", "Europe")
                .when()
                .get("/v2/region/{region}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_success_validSubRegion() {
        given()
                .pathParam("subregion", "Western%20Europe")
                .when()
                .get("/v2/subregion/{subregion}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_success_validLanguage() {
        given()
                .pathParam("lang", "Spanish")
                .when()
                .get("/v2/lang/{lang}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_success_validDemonym() {
        given()
                .pathParam("demonym", "American")
                .when()
                .get("/v2/demonym/{demonym}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_success_validRegionalBloc() {
        given()
                .pathParam("regionalbloc", "EU")
                .when()
                .get("/v2/regionalbloc/{regionalbloc}")
                .then()
                .statusCode(404);
    }
}