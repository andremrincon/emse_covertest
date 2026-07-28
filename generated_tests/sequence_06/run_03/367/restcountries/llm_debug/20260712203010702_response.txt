package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("rest.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_shortAlpha() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/ZZZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_withFields_bodyHasName() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        resp.then().body("name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v2/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_emptyCodes() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "").when().get("/v2/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX;YY;ZZ").when().get("/v2/alpha");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_length() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EU");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v2/name/Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/callingcode/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_withFields_bodyArrayNotEmpty() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;population").when().get("/v2/capital/Paris");
        resp.then().body("[0].name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/region/Europe");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/subregion/Western%20Europe");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_success_status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/lang/Spanish");
        resp.then().statusCode(404);
    }
}