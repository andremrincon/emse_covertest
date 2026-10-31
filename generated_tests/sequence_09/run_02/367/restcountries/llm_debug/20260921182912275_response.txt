package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_XYZ() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_Empty() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().queryParam("codes", "").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound_XXYYZZ() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ServerError_MalformedArray() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_Short() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound_XYZ() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound_123() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_NotFound_abc() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/abc").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_NotFound_123() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_NotFound_123() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/region/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_NotFound_123() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/subregion/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound_123() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testPostV1_MethodNotAllowed() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().post("/v1").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_Short1() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }
}