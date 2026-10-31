package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv().getOrDefault("API_BASE", System.getProperty("api.base", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1AlphaCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,CA,MX").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsMethodsHeaderOnV1AlphaByAlphacode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1CurrencyNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/XYZ").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlOnV1NameFullText() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France?fullText=false").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV2AlphaFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US?fields=name;capital;population").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV2AllFieldsQuery() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnContributePost() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType("application/json").body("{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_visa\"}").when().post("/contribute").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnRootGet() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/").then().header("Access-Control-Allow-Origin", nullValue());
    }
}