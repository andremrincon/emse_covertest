package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        Response response = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        Response response = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        Response response = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        Response response = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertNull(response.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAllCORSHeadersOnV1Endpoint() {
        Response response = given()
                .when()
                .get("/v1/all");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Origin"));
        Assert.assertNull(response2.getHeader("Access-Control-Allow-Methods"));
        Assert.assertNull(response2.getHeader("Access-Control-Allow-Headers"));
        Assert.assertNull(response2.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAllCORSHeadersOnV2Endpoint() {
        Response response = given()
                .when()
                .get("/v2/all");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Origin"));
        Assert.assertNull(response2.getHeader("Access-Control-Allow-Methods"));
        Assert.assertNull(response2.getHeader("Access-Control-Allow-Headers"));
        Assert.assertNull(response2.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCurrencyEndpoint() {
        Response response = given()
                .when()
                .get("/v1/currency/USD");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnNameEndpoint() {
        Response response = given()
                .when()
                .get("/v1/name/France");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCapitalEndpoint() {
        Response response = given()
                .when()
                .get("/v1/capital/London");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnRegionEndpoint() {
        Response response = given()
                .when()
                .get("/v1/region/Europe");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCallingCodeEndpoint() {
        Response response = given()
                .when()
                .get("/v1/callingcode/1");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Origin"));
        Assert.assertNull(response2.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnSubregionEndpoint() {
        Response response = given()
                .when()
                .get("/v1/subregion/Western Europe");

        response.then().statusCode(lessThan(300));

        Response response2 = given()
                .when()
                .get("/v1/subregion/Western Europe")
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response2.getHeader("Access-Control-Allow-Headers"));
        Assert.assertNull(response2.getHeader("Cache-Control"));
    }
}