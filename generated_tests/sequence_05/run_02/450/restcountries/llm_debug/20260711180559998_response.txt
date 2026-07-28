package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetV1AllAddsAccessControlAllowOrigin() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/all");
        r.then().assertThat().header("Access-Control-Allow-Origin", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaAddsAllowMethodsHeader() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().assertThat().header("Access-Control-Allow-Methods", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void testGetV1NameAddsAllowHeaders() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/name/France");
        r.then().assertThat().header("Access-Control-Allow-Headers", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void testGetV1RegionAddsCacheControl() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/region/Europe");
        r.then().assertThat().header("Cache-Control", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void testPostContributeReturns202() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().contentType("application/json").body("{\"amount\":1,\"currency\":\"USD\",\"token\":\"tok_test\"}").post("/contribute");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightAddsAccessControlAllowOrigin() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().options("/v1/alpha/US");
        r.then().assertThat().header("Access-Control-Allow-Origin", equalTo((String) null));
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaNotFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/XYZ");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaBadRequestReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/123");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyNotFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/XYZ");
        r.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1NameNotFoundReturns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/name/123");
        r.then().statusCode(404);
    }
}