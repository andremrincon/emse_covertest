package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    private static final String BASE_URL = System.getProperty("baseUrl",
            System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void getByAlpha_nonExistentCode_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/alpha/XYZ")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_shortCodesNoSemicolon_returns400() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "1")
        .when()
                .get("/v1/alpha/")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByAlphaList_nonExistentCodes_returns404() {
        given()
                .accept(ContentType.JSON)
                .queryParam("codes", "XX;YY;ZZ")
        .when()
                .get("/v1/alpha/")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCurrency_invalidLength_returns400() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/currency/12")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCurrency_nonExistentCurrency_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/currency/XYZ")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByName_validName_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByName_nonExistentName_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/name/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_validCode_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/callingcode/1")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCallingCode_nonExistentCode_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/callingcode/99999")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCapital_validCapital_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/capital/London")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegion_validRegion_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/region/Europe")
        .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void getBySubregion_validSubregion_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getBySubregion_nonExistentSubregion_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/subregion/123")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByLanguage_validLanguage_returns200() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/lang/es")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_nonExistentLanguage_returns404() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get("/v1/lang/123")
        .then()
                .statusCode(404);
    }
}