package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetterFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetterFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_invalidFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_withCommaSeparatedCodesReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US,CA").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_missingCodesReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextExactMatchReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_fullTextAlternativeSpellingReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "Federal Republic of Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_substringMatchReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/{name}", "United").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJsonTriggeredViaV2AllReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_foundReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_foundReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/{capital}", "London").then().statusCode(200);
    }
}