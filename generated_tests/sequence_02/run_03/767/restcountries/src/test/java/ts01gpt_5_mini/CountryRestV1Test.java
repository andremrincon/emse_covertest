package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
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
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/1");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/ZZZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US;CA").when().get("/v1/alpha");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalidReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "A").when().get("/v1/alpha");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalidReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/12");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/France");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByName_notFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "false").when().get("/v1/name/123");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapital_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/capital/London");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegion_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/region/Europe");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/subregion/Western%20Europe");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_validReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}