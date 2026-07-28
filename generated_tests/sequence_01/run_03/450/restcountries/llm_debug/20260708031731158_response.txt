package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_Short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/A");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess_Semicolon() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US;CA").when().get("/v1/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest_CommaSeparated() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest_MissingCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_InvalidLength() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess_France_FullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound_Numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess_London() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/London");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess_Europe() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        act.then().statusCode(200);
    }
}