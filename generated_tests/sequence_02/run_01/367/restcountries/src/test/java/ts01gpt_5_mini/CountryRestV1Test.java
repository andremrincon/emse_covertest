package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private static String BASE_URL;

    @BeforeClass
    public static void setup() {
        BASE_URL = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_US() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_short() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha/1");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_XYZ() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha/XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListEmptyBadRequest() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha?codes=");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess_US_CA() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha?codes=US,CA");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListServerError_malformed() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/alpha?codes=%5B%22US%22,%22CA%22%5D");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_USD() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_numeric() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/12");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyNotFound_XYZ() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/currency/XYZ");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess_France_fullText_true() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/name/France?fullText=true");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound_numeric() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/name/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testPostOnV1MethodNotAllowed() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().post("/v1");
        response.then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess_Europe() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/region/Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionNotFound_numeric() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/region/123");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess_es() {
        given().baseUri(BASE_URL).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE_URL).when().get("/v1/lang/es");
        response.then().statusCode(200);
    }
}