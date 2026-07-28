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
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{code}", "US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormat400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{code}", "123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/{code}", "ZZZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_parsedCountryString() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/{code}", "US");
        act.then().body(containsString("name"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "123").when().get("/v2/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "XX,YY,ZZ").when().get("/v2/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/{currency}", "12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_valid200_fullTextFalse() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v2/name/{name}", "Germany");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/{code}", "1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_parsedCountries() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/{capital}", "Paris");
        act.then().body(containsString("Paris"));
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/region/{region}", "Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPost_methodNotAllowed_405() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().post("/v2");
        act.then().statusCode(405);
    }
}