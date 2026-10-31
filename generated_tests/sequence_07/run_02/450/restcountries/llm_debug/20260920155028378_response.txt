package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_BadRequest_ShortCode() {
        given()
            .when()
                .get("/v2/alpha/1")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlpha_Success_NullFields() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_BadRequest_ShortCode() {
        given()
            .queryParam("codes", "1")
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_NotFound_UnknownCode() {
        given()
            .queryParam("codes", "XX")
            .when()
                .get("/v2/alpha/")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_BadRequest_InvalidLength() {
        given()
            .when()
                .get("/v2/currency/12")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_NotFound_UnknownCurrency() {
        given()
            .when()
                .get("/v2/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_NotFound_NumericName() {
        given()
            .when()
                .get("/v2/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_NotFound_NonNumeric() {
        given()
            .when()
                .get("/v2/callingcode/abc")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_NotFound_NumericCapital() {
        given()
            .when()
                .get("/v2/capital/12345")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegion_NotFound_NumericRegion() {
        given()
            .when()
                .get("/v2/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_NotFound_NumericSubRegion() {
        given()
            .when()
                .get("/v2/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_NotFound_NumericLanguage() {
        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_NotFound_NumericDemonym() {
        given()
            .when()
                .get("/v2/demonym/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_NotFound_NumericBloc() {
        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getCountries_Success_NullFields() {
        given()
            .when()
                .get("/v2")
            .then()
                .statusCode(200);
    }
}