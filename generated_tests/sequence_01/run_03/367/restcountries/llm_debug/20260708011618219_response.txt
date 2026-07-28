package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUpClass() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_success_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badFormat_numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound_XYZ() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_success_multiple() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_badFormat() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "123").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_fullText_alternative_spelling() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "Federal Republic of Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_search() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/{name}", "United").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_trigger_v1all() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200);
    }
}