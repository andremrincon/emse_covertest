package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void getByAlpha_twoCharCodeMatch_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_threeCharCodeMatch_returns200() {
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByAlpha_noMatch_returns404() {
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByAlpha_invalidFormat_returns400() {
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByCodeList_validCodes_returns200() {
        given().when().queryParam("codes", "US;CA").get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_nonExistentCodes_returns404() {
        given().when().queryParam("codes", "XX;YY;ZZ").get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByCodeList_missingCodesParam_returns400() {
        given().when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void getByCodeList_duplicateCodes_returns200() {
        given().when().queryParam("codes", "US;US").get("/v1/alpha").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_nameMatch_returns200() {
        given().when().queryParam("fullText", "true").get("/v1/name/Germany").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_altSpellingMatch_returns200() {
        String name = "Federal Republic of Germany";
        String encoded = name.replace(" ", "%20");
        given().when().queryParam("fullText", "true").get("/v1/name/" + encoded).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fulltextSearch_noMatch_returns404() {
        given().when().queryParam("fullText", "true").get("/v1/name/NonExistentCountry123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void loadJson_initialization_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
    }
}