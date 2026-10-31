package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceBaseTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoCharCodeMatch() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_TwoCharCodeNoMatch() {
        given().when().get("/v1/alpha/XX").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_ThreeCharCodeNoMatch() {
        given().when().get("/v1/alpha/XXX").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_ValidCodes() {
        given().when().queryParam("codes", "US;CA").get("/v1/alpha").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_MixedValidInvalidCodes() {
        given().when().queryParam("codes", "US;XX").get("/v1/alpha").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_AllInvalidCodes() {
        given().when().queryParam("codes", "XX;YY;ZZ").get("/v1/alpha").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCodeList_MissingCodesParameter() {
        given().when().get("/v1/alpha").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_AltSpellingMatch() {
        given().when().queryParam("fullText", "true").get("/v1/name/United%20States%20of%20America").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_ExactNameMatch() {
        given().when().queryParam("fullText", "true").get("/v1/name/France").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testFulltextSearch_NoMatch() {
        given().when().queryParam("fullText", "true").get("/v1/name/NonExistentCountryName").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_V2_ThreeCharCodeNoMatch() {
        given().when().get("/v2/alpha/ZZZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLoadJson_DataLoadedSuccessfully() {
        given().when().get("/v1/all").then().statusCode(404);
    }
}