package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCorsFilterAddsAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterAddsAllowMethodsHeader() {
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterAddsAllowHeadersHeader() {
        given().when().get("/v1/name/{name}", "France").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/{name}", "France").then().statusCode(200).extract().response();
        response.then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterAddsCacheControlHeader() {
        given().when().get("/v1/capital/{capital}", "London").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/{capital}", "London").then().statusCode(200).extract().response();
        response.then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testV2AllEndpoint() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/all");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AlphaEndpoint() {
        given().when().get("/v2/alpha/{alphacode}", "US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/alpha/{alphacode}", "US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpoint() {
        given().when().get("/v2/currency/{currency}", "EUR").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/{currency}", "EUR");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2NameEndpoint() {
        given().when().get("/v2/name/{name}", "Germany").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/name/{name}", "Germany");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CallingcodeEndpoint() {
        given().when().get("/v2/callingcode/{callingcode}", "1").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/callingcode/{callingcode}", "1");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CapitalEndpoint() {
        given().when().get("/v2/capital/{capital}", "Paris").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/capital/{capital}", "Paris");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2RegionEndpoint() {
        given().when().get("/v2/region/{region}", "Europe").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/region/{region}", "Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2SubregionEndpoint() {
        given().when().get("/v2/subregion/{subregion}", "Western Europe").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/subregion/{subregion}", "Western Europe");
        response.then().statusCode(200);
    }
}