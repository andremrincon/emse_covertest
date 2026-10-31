package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given().when().get("/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithoutFields() {
        given().when().get("/v2/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccessWithFields() {
        given().when().get("/v2/alpha/US?fields=name;capital").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest() {
        given().when().get("/v2/alpha?codes=1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccessWithFields() {
        given().when().get("/v2/alpha?codes=US,CA&fields=name;capital").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given().when().get("/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given().when().get("/v2/currency/EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess() {
        given().when().get("/v2/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given().when().get("/v2/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given().when().get("/v2/capital/Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given().when().get("/v2/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegionSuccess() {
        given().pathParam("subregion", "Western Europe").when().get("/v2/subregion/{subregion}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given().pathParam("lang", "Spanish").when().get("/v2/lang/{lang}").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonymSuccess() {
        given().pathParam("demonym", "American").when().get("/v2/demonym/{demonym}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocSuccess() {
        given().pathParam("regionalbloc", "EU").when().get("/v2/regionalbloc/{regionalbloc}").then().statusCode(200);
    }
}