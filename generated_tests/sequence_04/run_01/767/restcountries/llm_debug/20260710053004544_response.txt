package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @After
    public void tearDown() {
        RestAssured.reset();
    }

    @Test(timeout = 60000)
    public void corsAllowOriginHeaderOnV1All() {
        Response response = given()
            .when()
                .get("/v1/all");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsAllowMethodsHeaderOnV1Alpha() {
        Response response = given()
            .when()
                .get("/v1/alpha/US");
        assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void corsAllowHeadersHeaderOnV1Name() {
        Response response = given()
            .when()
                .get("/v1/name/France");
        assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void cacheControlHeaderOnV1Capital() {
        Response response = given()
            .when()
                .get("/v1/capital/London");
        assertNull(response.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOn404AlphaResponse() {
        Response response = given()
            .when()
                .get("/v1/alpha/XYZ");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOn400AlphaResponse() {
        Response response = given()
            .when()
                .get("/v1/alpha/123");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOnV2All() {
        Response response = given()
            .when()
                .get("/v2/all");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOnRootGet() {
        Response response = given()
            .when()
                .get("/");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOnPostToRoot() {
        Response response = given()
            .when()
                .post("/");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void chainDoFilterProducesResponseBody() {
        Response response = given()
            .when()
                .get("/v1/alpha/US");

        assertTrue(response.asString().contains("United States"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOnCallingCodeEndpoint() {
        Response response = given()
            .when()
                .get("/v1/callingcode/1");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void corsHeaderOnCurrencyEndpoint() {
        Response response = given()
            .when()
                .get("/v1/currency/USD");
        assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }
}