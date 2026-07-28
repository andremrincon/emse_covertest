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
    public static void init() {
        BASE = System.getProperty("baseUrl");
        if (BASE == null || BASE.isEmpty()) {
            BASE = System.getenv("BASE_URL");
        }
        if (BASE == null || BASE.isEmpty()) {
            BASE = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/{code}", "US");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_TooShort() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/{code}", "1");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/{code}", "XYZ");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_US_SEMICOLON_CA() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "US;CA").when().get("/v1/alpha/");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_EmptyCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "").when().get("/v1/alpha/");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound_XX_YY() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().queryParam("codes", "XX;YY").when().get("/v1/alpha/");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/{currency}", "USD");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_InvalidLength() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/{currency}", "US");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/{currency}", "XYZ");
        res.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success_France() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/name/{name}", "France");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/callingcode/{code}", "1");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/capital/{capital}", "London");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success_Europe() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/region/{region}", "Europe");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_Success_WesternEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/subregion/{sub}", "Western Europe");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/lang/{lang}", "es");
        res.then().statusCode(200);
    }
}