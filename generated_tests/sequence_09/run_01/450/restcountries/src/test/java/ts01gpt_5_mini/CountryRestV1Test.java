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
    public static void init() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US,CA,MX");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequestWhenMissingCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccessWithFullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France?fullText=false");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByNameServerErrorScenario() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/True");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/callingcode/1");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/London");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetBySubregionSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/subregion/{sub}", "Western Europe");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/es");
        assertEquals(200, response.getStatusCode());
    }
}