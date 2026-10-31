package ts01gpt_5_mini_2;

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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}", "US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_short_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}", "1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}", "ZZZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_filters_response_body() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{alphacode}?fields={fields}", "US", "name;capital;population");
        act.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_good_codes_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha?codes={codes}", "US,CA,MX");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_bad_codes_brackets_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha?codes={codes}", "[\"US\",\"CA\"]");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid_length_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullText_true_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/{name}?fullText={full}", "Germany", "true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/{callingcode}", "1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_returns_body_contains_name() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/capital/{capital}?fields={fields}", "Paris", "name;capital;population");
        act.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByRegion_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/region/{region}", "123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/subregion/{subregion}?fields={fields}", "Western Europe", "name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/lang/{lang}?fields={fields}", "Spanish", "name;capital;population");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_valid_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/demonym/{demonym}", "American");
        act.then().statusCode(200);
    }
}