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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCorsHeaderOnV1AlphaByCode200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV1AlphaList() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1AlphaNotFound() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1Currency() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV1NameWithFullTextQuery() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/France").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV1CallingCodeNotFound() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/abc").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnV2AllWithFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsOnRootPost() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().post("/").then().header("Access-Control-Allow-Methods", nullValue());
    }
}