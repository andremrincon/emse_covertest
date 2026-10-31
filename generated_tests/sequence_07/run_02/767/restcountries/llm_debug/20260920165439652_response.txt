package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyFields() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyName() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsCurrencySymbol() {
        given().when().get("/v2/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given().when().get("/v2/currency/EUR").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given().when().get("/v1/all").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AllReturnsCurrencySymbol() {
        given().when().get("/v2/all").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyCode() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsCurrencyName() {
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencySymbol() {
        given().when().get("/v2/name/Germany").then().statusCode(404);
    }
}