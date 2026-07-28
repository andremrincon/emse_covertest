package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryRestV2Test {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_Success_WithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fields", "name").when().get("/v2/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlpha_BadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/alpha/1");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("codes", "US;CA").queryParam("fields", "name").when().get("/v2/alpha/");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaList_BadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("codes", "1").when().get("/v2/alpha/");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCurrency_BadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/currency/12");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGetByName_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/name/Germany");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCode_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/callingcode/49");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByCapital_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/capital/Berlin");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegion_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/region/Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetBySubRegion_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/subregion/Western%20Europe");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/lang/de");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByDemonym_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/demonym/German");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Success() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/EU");
        response.then().statusCode(200);
    }
}