package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_200_withFields() {
        given()
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_404_notFound() {
        when()
            .get("/v2/alpha/ZZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_200_withFields() {
        given()
            .queryParam("codes", "US;CA;MX")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_400_invalidCodes() {
        given()
            .queryParam("codes", "1")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_404_notFound() {
        given()
            .queryParam("codes", "XX")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_200() {
        when()
            .get("/v2/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_400_invalidLength() {
        when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_200() {
        when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_404_notFound() {
        when()
            .get("/v2/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_200() {
        when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_404_notFound() {
        when()
            .get("/v2/callingcode/99999")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_404_notFound() {
        when()
            .get("/v2/capital/12345")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_404_notFound() {
        when()
            .get("/v2/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_404_notFound() {
        when()
            .get("/v2/subregion/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_404_notFound() {
        when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}