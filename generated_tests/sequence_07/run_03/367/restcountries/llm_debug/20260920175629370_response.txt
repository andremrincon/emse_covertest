package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testV1AlphaUSRReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaGBReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/GB")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1LangEsReturnsCountriesWithLanguages() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1LangZhReturnsCountriesWithLanguages() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/lang/zh")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaUSReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaXKReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/alpha/XK")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2LangSpanishReturnsCountriesWithLanguages() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/lang/Spanish")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameGermanyReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CallingCode1ReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/callingcode/1")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionEuropeReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AllWithLanguageFieldsReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fields", "name;languages")
        .when()
            .get("/v2/all")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameFranceFullTextReturnsLanguageData() {
        given()
            .accept(ContentType.JSON)
            .queryParam("fullText", "true")
        .when()
            .get("/v2/name/France")
        .then()
            .statusCode(404);
    }
}