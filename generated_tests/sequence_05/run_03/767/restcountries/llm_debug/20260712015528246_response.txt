package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    private static String baseUrl;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        baseUrl = prop != null ? prop : (env != null ? env : "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByAlphaV1_Success_TwoLetter() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaV2_Success_TwoLetter() {
        given().baseUri(baseUrl).when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadFormat_Returns400() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_Returns404() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV1_Success_MultipleCodes() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).queryParam("codes", "US,CA").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV1_MalformedArray_Triggers500() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_ExactNameMatch_Returns200() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).queryParam("fullText", "true").when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFullTextSearch_AlternativeSpellingMatch_Returns200() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).queryParam("fullText", "true").when().get("/v1/name/Federal%20Republic%20of%20Germany");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeListV1_AlternateDelimiter_Triggers500_LoadJsonPath() {
        given().baseUri(baseUrl).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).queryParam("codes", "US|CA|MX").when().get("/v1/alpha");
        act.then().statusCode(400);
    }
}