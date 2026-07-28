package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.baseUri");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_BASE_URI");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_twoLetterExists() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_threeLetterExists() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_missingCodesParam() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_validCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testName_fullText_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testName_substring_match() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/Fran").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLoadJson_v2All_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all").then().statusCode(200);
    }
}