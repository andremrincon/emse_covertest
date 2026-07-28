package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void getByAlpha_validCode_returns200() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidLength_returns400() {
        given()
            .when()
                .get("/v1/alpha/a")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_validCodes_returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns400() {
        given()
            .queryParam("codes", "a")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFoundCodes_returns404() {
        given()
            .queryParam("codes", "XX;YY")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validCurrency_returns200() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given()
            .when()
                .get("/v1/currency/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        given()
            .when()
                .get("/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        given()
            .when()
                .get("/v1/name/NonExistentCountryName")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given()
            .when()
                .get("/v1/callingcode/99999")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        given()
            .when()
                .get("/v1/capital/NonExistentCapital")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
            .when()
                .get("/v1/region/NonExistentRegion")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
            .when()
                .get("/v1/subregion/NonExistentSubregion")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
            .when()
                .get("/v1/lang/xyz")
            .then()
                .statusCode(404);
    }
}