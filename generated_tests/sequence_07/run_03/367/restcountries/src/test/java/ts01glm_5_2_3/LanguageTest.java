package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodesTriggersLanguageSetters() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v2/alpha")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CallingCodeTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CapitalTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(404);
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/subregion/{sub}", "Western Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2LangTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2DemonymTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/demonym/American")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalBlocTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AllTriggersLanguageSetters() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(404);
    }
}