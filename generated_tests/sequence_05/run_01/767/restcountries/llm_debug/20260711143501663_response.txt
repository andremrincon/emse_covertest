package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
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
    public void getByAlpha_success_noFields_returns200() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_badRequest_shortCode_returns400() {
        given()
                .queryParam("codes", "1")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(400);
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
    public void getByCurrency_badRequest_invalidLength_returns400() {
        given()
                .when()
                .get("/v2/currency/12")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_notFound_returns404() {
        given()
                .when()
                .get("/v2/currency/XYZ")
                .then()
                .statusCode(404);
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
                .get("/v2/callingcode/99999")
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
    public void getByRegion_notFound_returns404() {
        given()
                .when()
                .get("/v2/region/123")
                .then()
                .statusCode(404);
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