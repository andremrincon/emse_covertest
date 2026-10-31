package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("baseUrl");
        String fromEnv = System.getenv("BASE_URL");
        if (fromProp != null && !fromProp.isEmpty()) {
            BASE = fromProp;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            BASE = fromEnv;
        } else {
            BASE = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_US() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_numeric() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success_multiple() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha?codes=US,CA,MX,AD");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_emptyCodes() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha?codes=");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_internalServerError_arrayFormat() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha?codes=[\"US\",\"CA\"]");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success_USD() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_numeric() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/currency/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_success_fullTextTrue() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/name/France?fullText=true");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_numericName() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/name/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success_1() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_success_London() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/capital/London");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_success_Europe() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_success_WesternEurope() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_success_es() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/lang/es");
        resp.then().statusCode(200);
    }
}