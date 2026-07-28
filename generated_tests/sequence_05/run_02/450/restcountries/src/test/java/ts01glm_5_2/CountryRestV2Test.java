package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_successWithFields() {
        given()
                .queryParam("fields", "name;capital")
        .when()
                .get("/v2/alpha/US")
        .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test(timeout = 60000)
    public void getByAlpha_badRequest_invalidAlphaCode() {
        when()
                .get("/v2/alpha/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_successWithFields() {
        given()
                .queryParam("codes", "US;CA")
                .queryParam("fields", "name;capital")
        .when()
                .get("/v2/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_invalidCodesFormat() {
        given()
                .queryParam("codes", "1234")
        .when()
                .get("/v2/alpha/")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_unknownCodes() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
        .when()
                .get("/v2/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_success() {
        when()
                .get("/v2/currency/EUR")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_invalidLength() {
        when()
                .get("/v2/currency/12")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByName_success() {
        when()
                .get("/v2/name/Germany")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_success() {
        when()
                .get("/v2/callingcode/1")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_success() {
        when()
                .get("/v2/capital/Paris")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_success() {
        when()
                .get("/v2/region/Europe")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_success() {
        when()
                .get("/v2/subregion/Western%20Europe")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_success() {
        when()
                .get("/v2/lang/es")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByDemonym_success() {
        when()
                .get("/v2/demonym/American")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_success() {
        when()
                .get("/v2/regionalbloc/EU")
        .then()
                .statusCode(200);
    }
}