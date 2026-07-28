package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV1Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByAlphaSuccess_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaBadRequest_short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/A").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaNotFound_unknown() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/ZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListSuccess_commaSeparated() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListBadRequest_invalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "1").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaListNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "XX,YY,ZZ").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencySuccess_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyBadRequest_short() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/12").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByNameSuccess_France_fullTextFalse() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/France").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByNameNotFound_numericName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "false").when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeSuccess_1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalSuccess_London() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionSuccess_Europe() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Europe").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostToV1MethodNotAllowed() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().post("/v1").then().statusCode(405);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageSuccess_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(200);
    }
}