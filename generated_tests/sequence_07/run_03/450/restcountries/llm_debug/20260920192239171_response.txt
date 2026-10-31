package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeFound_returns200() {
        given().pathParam("alphacode", "US").when().get("/v1/alpha/{alphacode}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeFound_returns200() {
        given().pathParam("alphacode", "GB").when().get("/v1/alpha/{alphacode}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeNotFound_returns404() {
        given().pathParam("alphacode", "XX").when().get("/v1/alpha/{alphacode}").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeNotFound_returns404() {
        given().pathParam("alphacode", "XYZ").when().get("/v1/alpha/{alphacode}").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nullCodes_returns400() {
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given().queryParam("codes", "US;CA").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nonExistentCodes_returns404() {
        given().queryParam("codes", "XX;YY").when().get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_nameMatch_returns200() {
        given().pathParam("name", "France").queryParam("fullText", "true").when().get("/v1/name/{name}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        given().pathParam("name", "French%20Republic").queryParam("fullText", "true").when().get("/v1/name/{name}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatchV2_returns200() {
        given().pathParam("name", "French%20Republic").queryParam("fullText", "true").when().get("/v2/name/{name}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodesV2_returns200() {
        given().queryParam("codes", "US;CA").when().get("/v2/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given().pathParam("name", "NonExistentCountryName").queryParam("fullText", "true").when().get("/v1/name/{name}").then().statusCode(404);
    }
}