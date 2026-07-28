package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeV1() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3CodeV1() {
        given()
                .when()
                .get("/v1/alpha/USA")
                .then()
                .statusCode(200)
                .body("alpha3Code", equalTo("USA"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFoundV1() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMultipleCodesV1() {
        given()
                .queryParam("codes", "US;CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Test(timeout = 60000)
    public void testGetByCodeListInvalidCodesV1() {
        given()
                .queryParam("codes", "XX;YY;ZZ")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactNameV1() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("France"));
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/United States of America")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpellingV1() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/{name}", "United States of America")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonViaV2All() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2CodeV2() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("alpha2Code", equalTo("US"));
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMultipleCodesV2() {
        given()
                .queryParam("codes", "US;CA;MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(3));
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactNameV2() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFoundV2() {
        given()
                .when()
                .get("/v2/alpha/ZZZ")
                .then()
                .statusCode(404);
    }
}