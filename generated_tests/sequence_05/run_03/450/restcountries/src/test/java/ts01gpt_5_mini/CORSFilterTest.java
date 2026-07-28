package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        String prop = System.getProperty("api.base");
        base = Optional.ofNullable(env).orElse(Optional.ofNullable(prop).orElse("http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1All() {
        given().when().get(base + "/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/all");
        resp.then().header("Access-Control-Allow-Origin", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaUS() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().header("Access-Control-Allow-Methods", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnAlphaBadRequest() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/123");
        resp.then().header("Access-Control-Allow-Headers", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnAlphaNotFound() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/XYZ");
        resp.then().header("Cache-Control", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testStatusCode200V1AlphaUS() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsOriginOnCurrencyEndpoint() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/currency/USD");
        resp.then().header("Access-Control-Allow-Origin", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnNameEndpoint() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/name/France");
        resp.then().header("Access-Control-Allow-Origin", org.hamcrest.Matchers.nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV2AllWithFields() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/all?fields=name;capital;region;population;flag");
        resp.then().header("Access-Control-Allow-Origin", org.hamcrest.Matchers.nullValue());
    }
}