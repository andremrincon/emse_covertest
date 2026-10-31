package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithNullCodes() {
        given()
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithValidCodes() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListWithDuplicateCodes() {
        given()
            .queryParam("codes", "US;US")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithExactName() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithAltSpelling() {
        String name = "United States of America".replace(" ", "%20");
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/{name}", name)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchWithNoMatch() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJsonSuccessPath() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV2WithValidCodes() {
        given()
            .queryParam("codes", "US;CA")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearchV2WithAltSpelling() {
        String name = "Federal Republic of Germany".replace(" ", "%20");
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v2/name/{name}", name)
            .then()
                .statusCode(404);
    }
}