package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AlphaSingleCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2AlphaMultipleCodes() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Name() {
        given()
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2NameFullText() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Lang() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Currency() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2CallingCode() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Capital() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Region() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Subregion() {
        given()
                .when()
                .get("/v2/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Demonym() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2RegionalBloc() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404);
    }
}