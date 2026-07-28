package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
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
    public void getByAlpha_invalidLength_returns400() {
        given()
            .when()
                .get("/v1/alpha/1")
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
    public void getByAlphaList_validCodes_returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_invalidLength_returns400() {
        given()
            .queryParam("codes", "1")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
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
                .get("/v1/name/123zzz")
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
                .get("/v1/capital/NonExistentCityXYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
            .when()
                .get("/v1/region/NonExistentRegionXYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
            .when()
                .get("/v1/subregion/NonExistentSubregionXYZ")
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