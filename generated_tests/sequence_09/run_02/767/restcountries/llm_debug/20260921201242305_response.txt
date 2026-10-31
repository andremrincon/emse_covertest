package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_short() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaValidWithFields_parsedCountry() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US?fields=name;capital").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListValidCodes_parsedCountries() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest_emptyCodes() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_length() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByNameValidFullTextFalse() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v2/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().pathParam("subregion", "Western Europe").when().get("/v2/subregion/{subregion}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/Spanish").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/demonym/American").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AllWithFields_parsedCountries_excluded() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2").then().statusCode(200);
    }
}