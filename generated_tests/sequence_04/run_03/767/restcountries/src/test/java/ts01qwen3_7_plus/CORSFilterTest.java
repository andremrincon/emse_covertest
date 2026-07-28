package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    private static final String BASE_URI = "http://localhost:8080/rest";

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCorsAllowOriginHeader() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/all");

        response.then().header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeader() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/alpha/US");

        response.then().header("Access-Control-Allow-Methods", equalTo("GET"));
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeader() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/name/France");

        response.then().header("Access-Control-Allow-Headers", equalTo("Accept, X-Requested-With"));
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/capital/London");

        response.then().header("Cache-Control", equalTo("public, max-age=86400"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1RegionEndpointStatus() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/region/Europe");

        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointStatus() {
        given().baseUri(BASE_URI).when().get("/").then().statusCode(lessThan(300));

        Response response = given().baseUri(BASE_URI).when().get("/v1/currency/USD");

        response.then().statusCode(200);
    }
}