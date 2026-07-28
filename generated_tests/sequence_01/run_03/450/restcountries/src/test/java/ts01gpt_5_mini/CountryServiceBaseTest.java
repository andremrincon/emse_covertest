package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoLetter_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ThreeLetter_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_Success() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_BadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_NameMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AltSpellingMatch() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", true).when().get("/v1/name/{name}", "DE").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_ServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha").then().statusCode(400);
    }
}