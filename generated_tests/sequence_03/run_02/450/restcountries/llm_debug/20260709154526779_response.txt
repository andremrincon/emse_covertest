package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static final String BASE;
    static {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = System.getenv("API_BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080/rest";
        }
        BASE = b;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_US() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/alpha/US");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_Short() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/alpha/1");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_ZZZ() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/alpha/ZZZ");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_MissingCodes_BadRequest() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/alpha");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_Semicolon() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "US;CA").when().get(BASE + "/v1/alpha");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound_XXYY() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "XX;YY").when().get(BASE + "/v1/alpha");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_USD() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/currency/USD");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_Short() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/currency/12");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess_France() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fullText", "false").when().get(BASE + "/v1/name/France");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess_1() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/callingcode/1");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess_London() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/capital/London");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess_Europe() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/region/Europe");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionSuccess_WesternEurope() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().pathParam("sub", "Western Europe").when().get(BASE + "/v1/subregion/{sub}");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess_es() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/v1/lang/es");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostOnV1_MethodNotAllowed() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().post(BASE + "/v1");
        r.then().statusCode(405);
    }
}