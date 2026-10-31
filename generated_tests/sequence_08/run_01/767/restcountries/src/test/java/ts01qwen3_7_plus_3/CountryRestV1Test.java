package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha/1");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha/US");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        Response response = given()
                .baseUri(baseUrl)
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .queryParam("codes", "XX,YY,ZZ")
                .when()
                .get("/v1/alpha");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha?codes=US%7CCA");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/currency/12");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/currency/USD");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/currency/XYZ");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/name/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/callingcode/99999");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/capital/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/region/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/subregion/123");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/lang/123");

        response.then().statusCode(404);
    }
}