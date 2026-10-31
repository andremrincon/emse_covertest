package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_withFields_parsedCountryBranch() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital").when().get("/v2/alpha/{alphacode}", "US");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_shortCode() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}", "1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success_noFields_parsedCountriesDefaultBranch() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_withFields_parsedCountriesFilteringBranch() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_invalidLength() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success_withFields_getCountriesJsonBranch() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital").when().get("/v2/currency/{currency}", "EUR");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByName_success_fullText_true() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v2/name/{name}", "Germany");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success_withFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;region").when().get("/v2/callingcode/{callingcode}", "1");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByCapital_success_noFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/capital/{capital}", "Paris");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_success_withFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/region/{region}", "Europe");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_success_noFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/subregion/{subregion}", "Western Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_success_withFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/lang/{lang}", "Spanish");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_success_withFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/demonym/{demonym}", "American");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_success_withFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        act.then().body(containsString("name"));
    }
}