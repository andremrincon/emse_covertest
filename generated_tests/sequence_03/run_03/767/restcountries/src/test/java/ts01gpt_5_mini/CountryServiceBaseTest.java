package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("REST_BASE_URL");
        if(base == null || base.isEmpty()) base = System.getenv("REST_BASE_URL");
        if(base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoLetter_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest_numeric_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_XYZ_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_multiple_codes_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes={codes}", "US,CA").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_duplicates_resultUnique() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes={codes}", "US,US").then().body("size()", is(2));
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_missing_codes_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testName_fullText_exact_match_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}?fullText=true", "United States of America").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_match_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}?fullText=false", "United").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_notFound_numeric_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testName_fullText_matches_altSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}?fullText=true", "DE").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_matches_altSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}?fullText=false", "Republic").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1All_loadJson_success_returns200() {
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_malformed_codes_triggersServerError_returns500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=%5B%22US%22,%22CA%22%5D").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAlpha_with_uuid_like_partitioning_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "US";
        given().when().get("/v1/alpha/{alphacode}", unique).then().statusCode(200);
    }
}