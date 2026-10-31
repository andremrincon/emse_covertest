package ts01glm_5_2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void getByAlphaNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaBadRequest() {
        given()
            .when()
                .get(BASE_URL + "/v1/alpha/A")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequest() {
        given()
                .queryParam("codes", "ABCD")
            .when()
                .get(BASE_URL + "/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFound() {
        given()
                .queryParam("codes", "XX,YY,ZZ")
            .when()
                .get(BASE_URL + "/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequest() {
        given()
            .when()
                .get(BASE_URL + "/v1/currency/12")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/currency/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameServerError() {
        given()
            .when()
                .get(BASE_URL + "/v1/name/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/callingcode/abc")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeServerError() {
        given()
            .when()
                .get(BASE_URL + "/v1/callingcode/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/capital/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/region/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionServerError() {
        given()
            .when()
                .get(BASE_URL + "/v1/region/True")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubregionNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/subregion/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFound() {
        given()
            .when()
                .get(BASE_URL + "/v1/lang/123")
            .then()
                .statusCode(404);
    }
}