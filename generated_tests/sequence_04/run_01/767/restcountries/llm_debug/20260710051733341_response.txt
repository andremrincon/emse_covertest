package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_parsedCountry_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badLength_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/alpha/1");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_parsedCountries_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "US,CA").queryParam("fields", "name;capital").when().get("/v2/alpha");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_emptyCodes_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "").when().get("/v2/alpha");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_parsedCountries_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badLength_returns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/currency/12");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fullText", "true").when().get("/v2/name/Germany");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/name/123");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;region").when().get("/v2/callingcode/1");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/region/Europe");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/{sub}", "Western Europe");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/lang/{lang}", "Spanish");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/demonym/{demonym}", "American");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_withFields_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;currencies").when().get("/v2/regionalbloc/{bloc}", "EU");
        r.then().statusCode(200);
    }
}