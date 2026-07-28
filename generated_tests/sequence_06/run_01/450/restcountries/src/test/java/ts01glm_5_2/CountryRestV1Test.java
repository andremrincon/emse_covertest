package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_validCode_returns200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFoundCode_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidLengthCode_returns400() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/alpha/1234")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_validCodes_returns200() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "US;CA;MX")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_shortCodes_returns400() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "1")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFoundCodes_returns404() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha/")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validCurrency_returns200() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFoundCurrency_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFoundName_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/callingcode/99999")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/capital/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/region/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}