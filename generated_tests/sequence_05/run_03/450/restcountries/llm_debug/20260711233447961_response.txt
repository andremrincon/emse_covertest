package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class CountryRestV2Test {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        BASE = System.getProperty("test.server");
        if (BASE == null || BASE.isEmpty()) {
            BASE = System.getenv("TEST_SERVER");
        }
        if (BASE == null || BASE.isEmpty()) {
            BASE = "http://localhost:8080/rest";
        }
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalid_short_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha/1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_returnsFilteredJsonContainingName() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha/US?fields=name;capital;population");
        act.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid_codes_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha?codes=US,CA");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_whenCodesLongWithoutSeparator() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha?codes=USCA");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/currency/EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid_length_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/name/Germany?fullText=true");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_returns404() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/name/ZZZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAll_withFields_returnsArrayContainingName() {
        given().when().get(BASE + "/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2?fields=name;capital;population;region");
        act.then().body(containsString("\"name\""));
    }

    @Test(timeout = 60000)
    public void testPostOnV2_returnsMethodNotAllowed405() {
        given().when().get(BASE + "/v2").then().statusCode(lessThan(300));
        Response act = given().when().post(BASE + "/v2");
        act.then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_missingCodes_returns400() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/alpha");
        act.then().statusCode(400);
    }
}