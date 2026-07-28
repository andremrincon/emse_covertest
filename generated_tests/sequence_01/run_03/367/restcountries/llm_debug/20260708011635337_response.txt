package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormat() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_missingCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextTrue_found() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/London");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_valid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }
}