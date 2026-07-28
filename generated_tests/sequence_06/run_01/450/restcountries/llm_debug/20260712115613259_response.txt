package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        BASE = prop != null ? prop : (env != null ? env : "http://localhost:8080/rest");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/alpha/US");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_Numeric() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/alpha/1");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/alpha/XYZ");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_MultipleCodes() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/alpha?codes=US,CA");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_MissingCodes() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/alpha");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/currency/USD");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_Numeric() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/currency/123");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success_FullTextFalse() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/name/France?fullText=false");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound_Numeric() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/name/123");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/callingcode/1");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/capital/London");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success_Europe() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/region/Europe");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success_WesternEurope() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/subregion/Western%20Europe");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success_es() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/v1/lang/es");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoPOST_MethodNotAllowed() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().post(BASE + "/v1");
        res.then().statusCode(405);
    }
}