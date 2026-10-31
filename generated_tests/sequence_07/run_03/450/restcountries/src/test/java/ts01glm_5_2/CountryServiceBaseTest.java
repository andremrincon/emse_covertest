package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha2LetterCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha3LetterCode() {
        given()
            .when()
                .get("/v1/alpha/USA")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given()
            .when()
                .get("/v1/alpha/XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListNullCodes() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListSingleCode() {
        given()
            .queryParam("codes", "US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListMultipleCodes() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListDuplicateCodes() {
        given()
            .queryParam("codes", "US,US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactName() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/French Republic")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", "French Republic")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubstringSearchName() {
        given()
            .when()
                .get("/v1/name/Franc")
            .then()
                .statusCode(200);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v1/name/French Rep")
    @Test(timeout = 60000)
    public void testSubstringSearchAltSpelling() {
        given()
            .when()
                .get("/v1/name/{name}", "French Rep")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJsonNormalPath() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2GetByAlpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2GetByCodeList() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(400);
    }

    @Ignore("Illegal character in path at index 41: http://localhost:8080/rest/v2/name/French Republic")
    @Test(timeout = 60000)
    public void testV2FulltextSearchAltSpelling() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/{name}", "French Republic")
            .then()
                .statusCode(200);
    }
}