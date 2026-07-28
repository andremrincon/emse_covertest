package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    private String base() {
        String b = System.getenv("TEST_BASE_URL");
        if (b == null || b.isEmpty()) {
            b = System.getProperty("test.base.url", "http://localhost:8080/rest");
        }
        return b;
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_lengthOne_returnsBadRequest() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/alpha/1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_lengthFour_returnsBadRequest() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/alpha/ABCD");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_xyz_returnsNotFound() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByAlpha_us_returnsOk() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_codesOne_returnsBadRequest() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).queryParam("codes", "1").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_codesUS_returnsOk() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).queryParam("codes", "US").when().get("/v1/alpha");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByAlphaList_notFound_returnsNotFound() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_twoChars_returnsBadRequest() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/currency/12");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_xyz_returnsNotFound() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/currency/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByCurrency_usd_returnsOk() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByName_france_fullTextFalse_returnsOk() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).queryParam("fullText", "false").when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByName_numeric_returnsNotFound() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).queryParam("fullText", "false").when().get("/v1/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByCallingCode_one_returnsOk() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByCallingCode_notFound_returnsNotFound() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().get("/v1/callingcode/99999");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_doPOST_returnsMethodNotAllowed() {
        String base = base();
        given().baseUri(base).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(base).when().post("/v1");
        act.then().statusCode(405);
    }
}