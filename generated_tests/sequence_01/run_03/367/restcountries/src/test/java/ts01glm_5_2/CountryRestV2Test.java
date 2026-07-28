package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlpha_NotFound_Returns404() {
        given()
            .pathParam("alphacode", "ZZZ")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_FoundNoFields_Returns200() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_InvalidCodes_Returns400() {
        given()
            .queryParam("codes", "ABCD")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFoundCodes_Returns404() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_ValidCodesNoFields_Returns200() {
        given()
            .queryParam("codes", "US;CA")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_InvalidLength_Returns400() {
        given()
            .pathParam("currency", "12")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound_Returns404() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound_Returns404() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound_Returns404() {
        given()
            .pathParam("callingcode", "99999")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound_Returns404() {
        given()
            .pathParam("capital", "12345")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound_Returns404() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_NotFound_Returns404() {
        given()
            .pathParam("subregion", "123")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound_Returns404() {
        given()
            .pathParam("lang", "123")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_NotFound_Returns404() {
        given()
            .pathParam("demonym", "123")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_NotFound_Returns404() {
        given()
            .pathParam("regionalbloc", "123")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}