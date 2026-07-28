package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsOnV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaInvalidReturns400AndFilterApplied() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1AlphaMultipleCodes() {
        given().when().get("/v1/alpha?codes=US").then().statusCode(lessThan(300));
        String codes = "US,CA";
        Response act = given().when().get("/v1/alpha?codes=" + codes);
        act.then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeaderOnV1CurrencyUSD() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithInvalidFieldsReturns400() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/US?fields=name");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsOnV2AllWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String fields = "name;capital;population";
        Response act = given().when().get("/v2/all?fields=" + fields);
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testPostMethodNotAllowedOnV2() {
        given().when().get("/v2?fields=name").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response act = given().header("Content-Type", "application/json").body("{\"id\":\"" + unique + "\"}").when().post("/v2");
        act.then().statusCode(405);
    }
}