package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CountryRestV2Test {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
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
    public void getByAlpha_successWithoutFields_returns200() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_successWithFields_returns200() {
        given()
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequestShortCode_returns400() {
        given()
                .queryParam("codes", "1")
                .when()
                .get("/v2/alpha/")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_successWithFields_returns200() {
        given()
                .queryParam("codes", "US;CA")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_badRequestInvalidLength_returns400() {
        given()
                .when()
                .get("/v2/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_success_returns200() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_notFound_returns404() {
        given()
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_notFound_returns404() {
        given()
                .when()
                .get("/v2/callingcode/abc")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_notFound_returns404() {
        given()
                .when()
                .get("/v2/capital/12345")
                .then()
                .statusCode(404);
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
    public void getBySubRegion_notFound_returns404() {
        given()
                .when()
                .get("/v2/subregion/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_notFound_returns404() {
        given()
                .when()
                .get("/v2/lang/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_notFound_returns404() {
        given()
                .when()
                .get("/v2/demonym/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_notFound_returns404() {
        given()
                .when()
                .get("/v2/regionalbloc/123")
                .then()
                .statusCode(404);
    }
}