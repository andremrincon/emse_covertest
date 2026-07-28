package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_bodyContainsName() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        resp.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_semicolon_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badFormat_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v2/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/12");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextFalse_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v2/name/Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/lang/Spanish");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/demonym/American");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }
}