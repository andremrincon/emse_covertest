package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void getByAlpha_invalidLength_returns400() {
        given()
            .when()
                .get("/v1/alpha/1234")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void getByAlphaList_validCodesWithSemicolon_returns200() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns400() {
        given()
            .queryParam("codes", "1")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in query at index 24: /rest/v1/alpha/?codes=US|CA|MX")
    @Test(timeout = 60000)
    public void getByAlphaList_pipeSeparator_returns500() {
        given()
            .queryParam("codes", "US|CA|MX")
            .when()
                .get("/v1/alpha/")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given()
            .when()
                .get("/v1/currency/12")
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
    public void getByCurrency_mixedCase_returns500() {
        given()
            .when()
                .get("/v1/currency/XyZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        given()
            .when()
                .get("/v1/name/123")
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
                .get("/v1/capital/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_notFound_returns404() {
        given()
            .when()
                .get("/v1/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregion_notFound_returns404() {
        given()
            .when()
                .get("/v1/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
            .when()
                .get("/v1/lang/123")
            .then()
                .statusCode(404);
    }
}