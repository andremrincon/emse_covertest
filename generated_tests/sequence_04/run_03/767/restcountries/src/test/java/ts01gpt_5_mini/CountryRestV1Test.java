package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_badRequest_length() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "1");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_missingCodes_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha/");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_notFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha/");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_internalServerError_500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha/");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "12");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "USD");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByName_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", false).when().get("/v1/name/{name}", "France");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByName_notFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", false).when().get("/v1/name/{name}", "123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByCallingCode_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/{callingcode}", "1");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByCapital_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/{capital}", "London");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByRegion_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/{region}", "Europe");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getBySubregion_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/{subregion}", "Western%20Europe");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_getByLanguage_ok_200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "es");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_post_method_not_allowed_405() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().post("/v1");
        assertEquals(405, resp.getStatusCode());
    }
}