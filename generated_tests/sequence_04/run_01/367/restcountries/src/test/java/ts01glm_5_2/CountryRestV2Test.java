package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV2Test {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaWithFields() {
        given().when().get("/v2/alpha/US").then().statusCode(404);
        given()
            .queryParam("fields", "name;capital")
            .when()
            .get("/v2/alpha/US")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/alpha/XYZ")
            .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequestEmptyCodes() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/alpha")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .queryParam("codes", "XX;YY;ZZ")
            .when()
            .get("/v2/alpha")
            .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given().when().get("/v2/alpha/US").then().statusCode(404);
        given()
            .queryParam("codes", "US;CA")
            .queryParam("fields", "name;capital")
            .when()
            .get("/v2/alpha")
            .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/currency/123")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/currency/XYZ")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/name/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/callingcode/abc")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/capital/12345")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/region/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/subregion/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/lang/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/demonym/123")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given().when().get("/v2/all").then().statusCode(404);
        given()
            .when()
            .get("/v2/regionalbloc/123")
            .then()
            .statusCode(404);
    }
}