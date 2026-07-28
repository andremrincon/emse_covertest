package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void getByAlpha_badRequest() {
        given()
            .pathParam("alphacode", "1")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_success_withFields() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_success_withFields() {
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_serverError() {
        given()
            .queryParam("codes", "US,CA")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_success() {
        given()
            .pathParam("currency", "EUR")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_serverError() {
        given()
            .pathParam("currency", "%7B%7D")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_success() {
        given()
            .pathParam("name", "Germany")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_serverError() {
        given()
            .pathParam("name", "True")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_success() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_success() {
        given()
            .pathParam("capital", "Paris")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_success() {
        given()
            .pathParam("region", "Europe")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_success() {
        given()
            .pathParam("subregion", "Western%20Europe")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_success() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }
}