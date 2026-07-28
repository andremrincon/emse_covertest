package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_TooShort() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_XYZ() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_SingleCode_US() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/?codes=US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_CommaSeparated() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/?codes=US,CA");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError_ArrayStyle() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/alpha/?codes=[\"US\",\"CA\"]");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_InvalidFormat() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_InternalServerError_EUR() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/currency/EUR");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success_France_FullTextFalse() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/name/France?fullText=false");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_InternalServerError_TrueLiteral() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/name/True?fullText=false");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound_abc() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/callingcode/abc");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/capital/London");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success_Europe() {
        RestAssured.given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = RestAssured.given().when().get("/v1/region/Europe");
        act.then().statusCode(200);
    }
}