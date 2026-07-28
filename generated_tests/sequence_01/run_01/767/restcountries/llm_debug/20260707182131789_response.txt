package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_200_withFields() {
        given()
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_404_notFound() {
        given()
        .when()
            .get("/v2/alpha/ZZZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_200_withFields() {
        given()
            .queryParam("codes", "US;CA;MX")
            .queryParam("fields", "name;capital;population")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_400_badRequest() {
        given()
            .queryParam("codes", "abcd")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_404_notFound() {
        given()
            .queryParam("codes", "XX;YY;ZZ")
        .when()
            .get("/v2/alpha/")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_400_badRequest() {
        given()
        .when()
            .get("/v2/currency/12")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_404_notFound() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_200_success() {
        given()
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_200_success() {
        given()
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCapital_200_success() {
        given()
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_200_success() {
        given()
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubRegion_200_success() {
        given()
        .when()
            .get("/v2/subregion/Western%20Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_200_success() {
        given()
        .when()
            .get("/v2/lang/Spanish")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByDemonym_200_success() {
        given()
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_200_success() {
        given()
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }
}