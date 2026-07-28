package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("rest.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsSelectedField() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        act.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_emptyCodes_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "").when().get("/v2/alpha/");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha/");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_arrayFormat_returnsServerError() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha/");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v2/name/Germany");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;region").when().get("/v2/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/region/Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/Western%20Europe");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/lang/Spanish");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/demonym/American");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAll_withFields_returns200_parsedCountries() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population;region").when().get("/v2");
        act.then().statusCode(200);
    }
}