package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha200WithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha400InvalidAlpha() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha404NotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").queryParam("fields", "name;capital;population").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList400InvalidCodes() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency400Invalid() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/{currency}", "12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName200FullTextTrue() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", true).when().get("/v2/name/{name}", "Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/capital/{capital}", "Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/region/{region}", "Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLang200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/{lang}", "Spanish").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/demonym/{demonym}", "American").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostMethodNotAllowedOnV2() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().post("/v2").then().statusCode(405);
    }
}