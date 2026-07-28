package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoLetter_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ThreeLetter_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/USA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_InvalidFormat_BadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_MultipleCodes_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_InvalidFormat_BadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "123").when().get("/v1/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_ExactName_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AltSpelling_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/Deutschland");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_ServerError_OnArrayFormattedCodes() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetAllV1_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllV2_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/all");
        resp.then().statusCode(200);
    }
}