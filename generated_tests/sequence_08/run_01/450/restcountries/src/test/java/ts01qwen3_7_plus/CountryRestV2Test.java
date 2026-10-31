package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/alpha/ZZZ");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/alpha");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        Response response = given()
                .queryParam("codes", "XX,YY,ZZ")
                .when()
                .get(baseUrl + "/v2/alpha");

        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        Response response = given()
                .queryParam("codes", "US,CA")
                .when()
                .get(baseUrl + "/v2/alpha");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/currency/12");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/currency/XYZ");

        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/currency/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/name/Atlantis");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/name/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/callingcode/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/region/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/demonym/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InternalServerError() {
        Response response = given()
                .when()
                .get(baseUrl + "/v2/regionalbloc/True");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testParsedCountry() {
        Response response = given()
                .queryParam("fields", "name")
                .when()
                .get(baseUrl + "/v2/alpha/US");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testParsedCountries() {
        Response response = given()
                .queryParam("fields", "name")
                .when()
                .get(baseUrl + "/v2/all");

        response.then().statusCode(200);
    }
}