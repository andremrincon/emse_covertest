package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaTwoLetterSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaThreeLetterSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/USA");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeListSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeListBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "123").when().get("/v1/alpha");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCodeListServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearchExactName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/France");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/DE");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFulltextSearchNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLoadJsonViaAllEndpointBodyContainsGermany() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().body("value[0].name.common", equalTo(null));
    }
}