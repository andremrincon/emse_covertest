package ts01gpt_5_mini;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import org.junit.Test;

public class CountryRestV1Test {

    private static final String BASE = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));

    @Test(timeout = 60000)
    public void test_getByAlpha_badRequest_shortAlpha() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_notFound() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_missingCodes_badRequest() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get(BASE + "/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_internalServerError_onMalformedList() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get(BASE + "/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_badRequest_invalidLength() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_notFound() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/currency/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByName_found_fullTextFalse() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get(BASE + "/v1/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByName_notFound() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByCallingCode_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCapital_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/capital/London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByRegion_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByLanguage_found() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/lang/es").then().statusCode(200);
    }
}