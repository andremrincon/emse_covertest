package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void getByAlphaNotFoundTest() {
        given()
            .pathParam("alphacode", "ZZZ")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaBadRequestShortCodeTest() {
        given()
            .pathParam("alphacode", "A")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaWithFieldsTest() {
        given()
            .pathParam("alphacode", "US")
            .queryParam("fields", "name")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaListBadRequestTest() {
        given()
            .queryParam("codes", "A")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaListNotFoundTest() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrencyBadRequestTest() {
        given()
            .pathParam("currency", "AB")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrencyNotFoundTest() {
        given()
            .pathParam("currency", "XYZ")
        .when()
            .get("/v2/currency/{currency}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByNameNotFoundTest() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v2/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCodeNotFoundTest() {
        given()
            .pathParam("callingcode", "99999")
        .when()
            .get("/v2/callingcode/{callingcode}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapitalNotFoundTest() {
        given()
            .pathParam("capital", "12345")
        .when()
            .get("/v2/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionNotFoundTest() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v2/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getBySubRegionNotFoundTest() {
        given()
        .when()
            .get("/v2/all?fields=name")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("subregion", "123")
        .when()
            .get("/v2/subregion/{subregion}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguageNotFoundTest() {
        given()
            .pathParam("lang", "123")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonymNotFoundTest() {
        given()
            .pathParam("demonym", "123")
        .when()
            .get("/v2/demonym/{demonym}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBlocNotFoundTest() {
        given()
            .pathParam("regionalbloc", "123")
        .when()
            .get("/v2/regionalbloc/{regionalbloc}")
        .then()
            .statusCode(404);
    }
}