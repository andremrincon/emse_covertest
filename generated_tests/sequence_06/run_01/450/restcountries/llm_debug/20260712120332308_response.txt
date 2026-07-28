package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/alpha/1")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ValidWithFields() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Valid() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
            .queryParam("codes", "US")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InternalError() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/currency/%7B%7D")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_ValidWithFields() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
            .queryParam("fields", "name")
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Valid() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Valid() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_NotFound() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Valid() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_NotFound() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/demonym/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Valid() {
        given()
            .baseUri("http://localhost:8080")
            .basePath("/rest")
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }
}