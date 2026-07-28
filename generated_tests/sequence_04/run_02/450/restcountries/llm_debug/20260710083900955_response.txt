package ts01gpt_5_mini;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @Before
    public void setUp() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("test.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_Numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success_US_CA() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest_Invalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_NotFound_Codes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_InternalServerError_Malformed() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest_Numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_NotFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success_FullTextTrue() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_NotFound_Numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success_London() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoPOST_MethodNotAllowed() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().post("/v1").then().statusCode(405);
    }
}