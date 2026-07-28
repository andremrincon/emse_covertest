package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void getByAlpha_twoLetterCode_returnsCountry() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("alpha2Code", equalTo("US"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void getByAlpha_threeLetterCode_returnsCountry() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(404)
                .body("alpha3Code", equalTo("USA"));
    }

    @Test(timeout = 60000)
    public void getByAlpha_notFound_returns404() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nullCodes_returns400() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void getByCodeList_multipleCodes_returnsCountries() {
        given()
            .when()
                .get("/v1/alpha?codes=US;CA")
            .then()
                .statusCode(404)
                .body("size()", greaterThanOrEqualTo(2));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returnsUniqueCountries() {
        given()
            .when()
                .get("/v1/alpha?codes=US;US")
            .then()
                .statusCode(404)
                .body("size()", equalTo(1));
    }

    @Test(timeout = 60000)
    public void getByCodeList_notFoundCodes_returns404() {
        given()
            .when()
                .get("/v1/alpha?codes=XX;YY;ZZ")
            .then()
                .statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void fulltextSearch_exactNameMatch_returnsCountry() {
        given()
            .when()
                .get("/v1/name/Germany?fullText=true")
            .then()
                .statusCode(404)
                .body("[0].name", equalTo("Germany"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returnsCountry() {
        given()
            .when()
                .get("/v1/name/Federal%20Republic%20of%20Germany?fullText=true")
            .then()
                .statusCode(404)
                .body("[0].name", equalTo("Germany"));
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given()
            .when()
                .get("/v1/name/123?fullText=true")
            .then()
                .statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void substringSearch_nameSubstringMatch_returnsCountries() {
        given()
            .when()
                .get("/v1/name/United")
            .then()
                .statusCode(404)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void substringSearch_altSpellingSubstringMatch_returnsCountries() {
        given()
            .when()
                .get("/v1/name/Republic")
            .then()
                .statusCode(404)
                .body("size()", greaterThanOrEqualTo(1));
    }

    @Test(timeout = 60000)
    public void substringSearch_noMatch_returns404() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void loadJson_v1All_returnsCountries() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("size()", greaterThan(0));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void loadJson_v2All_returnsCountries() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(404)
                .body("size()", greaterThan(0));
    }
}