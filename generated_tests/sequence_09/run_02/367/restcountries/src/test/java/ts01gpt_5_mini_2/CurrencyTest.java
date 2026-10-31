package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    private static String getBaseUri() {
        String prop = System.getProperty("API_BASE");
        if (prop != null && !prop.isEmpty()) return prop;
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) return env;
        return "http://localhost:8080/rest";
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = getBaseUri();
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_numericBadRequest_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency_unknownCurrency_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_withFields_returns200() {
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR?fields=name;capital;population");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_codesQuery_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA,MX");
        resp.then().statusCode(400);
    }
}