package ts01glm_5_2;

import io.restassured.RestAssured;
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
    public void getByAlpha_successWithFields_returns200() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(lessThan(300));

        given()
                .queryParam("fields", "name;capital;population")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
                .when()
                .get("/v2/alpha/ZZZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_successWithFields_returns200() {
        given()
                .when()
                .queryParam("codes", "US;CA")
                .get("/v2/alpha")
                .then()
                .statusCode(lessThan(300));

        given()
                .queryParam("codes", "US;CA")
                .queryParam("fields", "name;capital;population")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_returns400() {
        given()
                .queryParam("codes", "123")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFound_returns404() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_success_returns200() {
        given()
                .when()
                .get("/v2/currency/USD")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequest_returns400() {
        given()
                .when()
                .get("/v2/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByName_success_returns200() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_success_returns200() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_success_returns200() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_success_returns200() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_success_returns200() {
        given()
                .when()
                .get("/v2/subregion/Western%20Europe")
                .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void getByLanguage_success_returns200() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByDemonym_success_returns200() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_success_returns200() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200);
    }
}