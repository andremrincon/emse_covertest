package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    private static String BASE_URL;

    @BeforeClass
    public static void setUp() {
        String fromProp = System.getProperty("baseUrl");
        String fromEnv = System.getenv("BASE_URL");
        if (fromProp != null && !fromProp.isEmpty()) {
            BASE_URL = fromProp;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            BASE_URL = fromEnv;
        } else {
            BASE_URL = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequestShortCode() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).when().get("/v2/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequestEmptyCodes() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("codes", "").when().get("/v2/alpha/");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequestInvalidCode() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).when().get("/v2/currency/12");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccessFullTextFalseWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fullText", "false").queryParam("fields", "name;capital;population").when().get("/v2/name/Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFoundNumericName() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).when().get("/v2/name/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;region").when().get("/v2/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/lang/Spanish");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymSuccessWithFields() {
        given().baseUri(BASE_URL).when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE_URL).queryParam("fields", "name;capital;population").when().get("/v2/demonym/American");
        resp.then().statusCode(200);
    }
}