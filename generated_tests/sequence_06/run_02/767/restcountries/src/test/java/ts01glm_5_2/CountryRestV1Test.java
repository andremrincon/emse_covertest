package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlpha_validCode_returns200() {
        RestAssured.given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidCode_returns400() {
        RestAssured.given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFoundCode_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_validCodes_returns200() {
        RestAssured.given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns400() {
        RestAssured.given()
                .queryParam("codes", "123")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFoundCodes_returns404() {
        RestAssured.given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validCurrency_returns200() {
        RestAssured.given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidCurrency_returns400() {
        RestAssured.given()
                .when()
                .get("/v1/currency/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFoundCurrency_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        RestAssured.given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}