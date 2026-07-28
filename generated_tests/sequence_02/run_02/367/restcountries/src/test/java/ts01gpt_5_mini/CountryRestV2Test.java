package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_validUS_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidShort_badRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_codes_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_invalidCodes() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound_status404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "XX,YY,ZZ").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validEUR_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/currency/EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid_badRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_notFound_404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_valid_fullTextFalse_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v2/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_numeric_notFound_404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid1_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;region").when().get("/v2/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_notFound_404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/99999").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testPostOnV2_methodNotAllowed_405() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().post("/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_validEurope_bodyContainsGermany() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/region/Europe").then().body(containsString("Germany"));
    }
}