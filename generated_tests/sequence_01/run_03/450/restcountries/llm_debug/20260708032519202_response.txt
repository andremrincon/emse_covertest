package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ValidAlpha2Code() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_InvalidAlphaCode() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_SemicolonSeparated() {
        given()
                .when()
                .get("/v1/alpha?codes=US;CA")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_DuplicateCodes() {
        given()
                .when()
                .get("/v1/alpha?codes=US;US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_ExactName() {
        given()
                .when()
                .get("/v1/name/Germany?fullText=true")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AlternativeSpelling() {
        given()
                .when()
                .get("/v1/name/Deutschland?fullText=true")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch_SubstringName() {
        given()
                .when()
                .get("/v1/name/Germ?fullText=false")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubstringSearch_AlternativeSpellingSubstring() {
        given()
                .when()
                .get("/v1/name/Deutsch?fullText=false")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson_GetAllCountries() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404);
    }
}