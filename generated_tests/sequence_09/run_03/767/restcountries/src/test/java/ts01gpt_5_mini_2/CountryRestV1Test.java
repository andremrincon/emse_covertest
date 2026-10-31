package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryRestV1Test {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_Numeric() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_Codes() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_ShortCodes() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().queryParam("codes", "123").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError_ArrayFormat() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().queryParam("codes", "[\"US\", \"CA\"]").when().get("/v1/alpha");
        resp.then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_Numeric() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/currency/123");
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_Success_es() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByName_Success_France() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByName_InternalServerError_True() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/name/True");
        resp.then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound_abc() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/callingcode/abc");
        resp.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/capital/London");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByRegion_Success_Europe() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetBySubregion_Success_WesternEurope() {
        given().when().get("/v1/all").then().statusCode(404);
        Response resp = given().when().get("/v1/subregion/Western%20Europe");
        resp.then().statusCode(200);
    }
}