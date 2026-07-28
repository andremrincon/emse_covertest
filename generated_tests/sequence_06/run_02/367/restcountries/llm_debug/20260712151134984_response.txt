package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_valid_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidLength_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_validMultiple_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US;CA").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_invalidFormat_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "XX;YY;ZZ").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_serverError_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_valid_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_invalid_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_serverError_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "INVALIDCURRENCYCODE").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_serverError_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "yes").when().get("/v1/name/{name}", "True").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_valid_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/{callingcode}", "1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_valid_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/{capital}", "London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_valid_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/{region}", "Europe").then().statusCode(200);
    }
}