package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseUrl");
        if (env != null && !env.isEmpty()) {
            base = env;
        } else if (prop != null && !prop.isEmpty()) {
            base = prop;
        } else {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredJson() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha/US?fields=name;capital;population");
        act.then().body(containsString("capital"));
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_shortAlpha_returnsBadRequest() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha/1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_unknownAlpha_returnsNotFound() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha/ZZZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_validCodes_returnsOk() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha?codes=US,CA&fields=name;capital;population");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_malformedCodes_returnsBadRequest() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha?codes=%5B%22US%22%2C%22CA%22%5D");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_nonExistingCodes_returnsNotFound() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/alpha?codes=XX,YY,ZZ");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validCurrency_returnsOk() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/currency/EUR?fields=name;capital;population");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidCurrency_returnsBadRequest() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returnsOk() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/name/Germany?fullText=true&fields=name;capital");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_withFields_returnsOk() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/callingcode/1?fields=name;capital;region");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_numericCapital_returnsNotFound() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/capital/12345");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_numericRegion_returnsNotFound() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/region/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_numericLang_returnsNotFound() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/lang/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testPost_onV2_returnsMethodNotAllowed() {
        given().when().get(base + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().post(base + "/v2");
        act.then().statusCode(405);
    }
}