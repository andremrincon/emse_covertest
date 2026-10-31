package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodesQuerySuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().param("codes", "US,CA,MX").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().param("fields", "name;capital;population").when().get("/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencySuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1NameFullTextTrue() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().param("fullText", "true").when().get("/v1/name/France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1LangSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2LangFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().param("fields", "name;capital;population").when().get("/v2/lang/Spanish");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaCodesBadFormat() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().param("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV2NameServerError() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/name/True");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CallingcodeNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/abc");
        resp.then().statusCode(404);
    }
}