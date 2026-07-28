package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
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
    public void getByAlpha_badRequest_shortAlphaCode() {
        given()
            .when()
                .get("/v2/alpha/1")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_success_withoutFields() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_success_withFields() {
        given()
            .when()
                .queryParam("fields", "name;capital")
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_emptyCodes() {
        given()
            .when()
                .queryParam("codes", "")
                .get("/v2/alpha/")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_success() {
        given()
            .when()
                .queryParam("codes", "US;CA")
                .get("/v2/alpha/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_success() {
        given()
            .when()
                .get("/v2/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_success() {
        given()
            .when()
                .get("/v2/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_notFound() {
        given()
            .when()
                .get("/v2/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_success() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_success() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_success() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_success() {
        given()
            .when()
                .get("/v2/subregion/Western%20Europe")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_success() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_success() {
        given()
            .when()
                .get("/v2/demonym/American")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_success() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }
}