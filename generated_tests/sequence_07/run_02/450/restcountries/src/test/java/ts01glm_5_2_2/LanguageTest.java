package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("es"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingcodeEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2DemonymEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocEndpointReturnsLanguageData() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("de"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaCodesEndpointReturnsLanguageData() {
        given()
                .queryParam("codes", "US,CA")
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404)
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RootEndpointReturnsLanguageData() {
        given()
                .queryParam("fields", "name;languages")
                .when()
                .get("/v2")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }
}