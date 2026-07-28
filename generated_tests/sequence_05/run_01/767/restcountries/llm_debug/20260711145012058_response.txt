package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1AlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowHeadersOnV1CurrencyNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/XYZ");
        act.then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1RegionSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnContributeAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnRootGet() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/");
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }
}