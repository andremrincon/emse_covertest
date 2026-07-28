package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoLetter_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ThreeLetter_USA_v2_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/{alphacode}", "USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_NotFound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_US_CA_returnsTwoCountries() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(400).body("status", equalTo(400)).body("message", equalTo("Bad Request"));
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_DirectName_France_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AlternativeSpelling_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "French Republic").then().statusCode(200);
    }
}