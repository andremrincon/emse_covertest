package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withValidAlphaAndFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withTooShortAlpha_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withNonExistingAlpha_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_withSemicolonSeparatedCodes_andFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "US;CA").queryParam("fields", "name;capital;population").when().get("/v2/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_withSingleCharCodes_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "A").when().get("/v2/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_withInvalidLength_returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/US").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_withUnknownCurrency_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v2/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_validCode_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testParsedCountries_withoutFields_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoPOST_returnsMethodNotAllowed() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().post("/v2").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_withInvalidRegion_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_notFound_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/123").then().statusCode(404);
    }
}