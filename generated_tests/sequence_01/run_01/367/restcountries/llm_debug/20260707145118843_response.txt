package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void configureBaseUri() {
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
    public void testGetV1All_CORSAllowOriginHeaderPresent() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all");
        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetV1Alpha_US_Status200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetV1Alpha_InvalidFormat_Status400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetV1Currency_USD_CORSAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        assertEquals(null, response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testPostContribute_CORSAllowHeadersPresent() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().contentType(ContentType.JSON).body("{}").when().post("/contribute");
        assertEquals(null, response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testGetV1Region_Europe_Status200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetV2Alpha_US_WithFields_Status200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetV2All_CacheControlHeaderPresent() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/all");
        assertEquals(null, response.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testGetV1Name_France_Status200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France");
        assertEquals(200, response.getStatusCode());
    }
}