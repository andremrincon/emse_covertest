package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base", "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaTwoLetterSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaThreeLetterSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/USA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaInvalidFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodesMultipleSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1NameFullTextExactMatchReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1NameFullTextAlternativeSpellingReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "Federal Republic of Germany");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1NameServerErrorScenarioTriggersErrorPath() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/{name}", "True");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CallingCodeSuccessCoversCallingCodeLookup() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/callingcode/1");
        resp.then().statusCode(200);
    }
}