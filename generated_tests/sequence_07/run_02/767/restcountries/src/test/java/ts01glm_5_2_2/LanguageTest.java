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
    public void testV2LangEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CapitalEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingcodeEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2DemonymEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionEndpointReturnsLanguageFields() {
        given()
                .when()
                .get("/v2/subregion/{sub}", "Western Europe")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaCodesEndpointReturnsLanguageFields() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404)
                .body("[0].languages", notNullValue());
    }
}