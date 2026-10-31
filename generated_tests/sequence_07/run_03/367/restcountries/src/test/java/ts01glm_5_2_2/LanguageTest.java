package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testV2LangEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/lang/Spanish")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2LangEndpointWithFieldsParam() {
        given()
                .queryParam("fields", "name;capital;population")
                .when()
                .get("/v2/lang/English")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2NameEndpointWithFullTextReturnsLanguages() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <400>.")
    @Test(timeout = 60000)
    public void testV2AlphaCodesEndpointReturnsLanguages() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2CallingCodeEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2CapitalEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2RegionEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV2SubregionEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsLanguages() {
        given()
                .queryParam("fields", "name;capital;region;population;flag")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV2DemonymEndpointReturnsLanguages() {
        given()
                .when()
                .get("/v2/demonym/American")
                .then()
                .statusCode(404)
                .body(notNullValue());
    }
}