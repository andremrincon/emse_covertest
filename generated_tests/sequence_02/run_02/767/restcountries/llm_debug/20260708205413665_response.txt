package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
                .pathParam("alphacode", "ZZZ")
                .when()
                .get("/v2/alpha/{alphacode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_validWithFields_returns200() {
        given()
                .pathParam("alphacode", "US")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/alpha/{alphacode}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_invalidCodes_returns400() {
        given()
                .queryParam("codes", "123")
                .when()
                .get("/v2/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_notFoundCodes_returns404() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v2/alpha/")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given()
                .pathParam("currency", "123")
                .when()
                .get("/v2/currency/{currency}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_validWithFields_returns200() {
        given()
                .pathParam("currency", "EUR")
                .queryParam("fields", "name;capital")
                .when()
                .get("/v2/currency/{currency}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        given()
                .pathParam("currency", "XYZ")
                .when()
                .get("/v2/currency/{currency}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_valid_returns200() {
        given()
                .pathParam("name", "France")
                .when()
                .get("/v2/name/{name}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_valid_returns200() {
        given()
                .pathParam("callingcode", "1")
                .when()
                .get("/v2/callingcode/{callingcode}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_valid_returns200() {
        given()
                .pathParam("capital", "Paris")
                .when()
                .get("/v2/capital/{capital}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_valid_returns200() {
        given()
                .pathParam("region", "Europe")
                .when()
                .get("/v2/region/{region}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_valid_returns200() throws Exception {
        String subregion = java.net.URLEncoder.encode("Western Europe", "UTF-8").replace("+", "%20");
        given()
                .when()
                .get("/v2/subregion/{subregion}", subregion)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_valid_returns200() {
        given()
                .pathParam("lang", "es")
                .when()
                .get("/v2/lang/{lang}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByDemonym_valid_returns200() {
        given()
                .pathParam("demonym", "American")
                .when()
                .get("/v2/demonym/{demonym}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_valid_returns200() {
        given()
                .pathParam("regionalbloc", "EU")
                .when()
                .get("/v2/regionalbloc/{regionalbloc}")
                .then()
                .statusCode(200);
    }
}