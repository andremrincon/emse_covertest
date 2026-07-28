package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ok_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_badRequest_short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_notFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_ok_semicolon() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US;CA").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_badRequest_commaList() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_empty_codes_badRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_ok_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_badRequest_short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_ok_fullText_false() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByName_notFound_numeric() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_ok_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_ok_London() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_ok_Europe() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubregion_ok_WesternEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/subregion/{sub}", "Western Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ok_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(200);
    }
}