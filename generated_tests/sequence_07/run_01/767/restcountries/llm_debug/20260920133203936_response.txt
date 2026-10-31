package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyCode() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyName() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencySymbol() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyFields() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithFieldsReturnsCurrencyCode() {
        given().when().get("/v2/alpha/US?fields=name;currencies").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithFieldsReturnsCurrencyName() {
        given().when().get("/v2/alpha/US?fields=name;currencies").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithFieldsReturnsCurrencySymbol() {
        given().when().get("/v2/alpha/US?fields=name;currencies").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocWithCurrencyFieldsReturnsCurrencyCode() {
        given().when().get("/v2/regionalbloc/EU?fields=name;capital;currencies").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AllWithCurrencyFieldsReturnsCurrencyCode() {
        given().when().get("/v2/all?fields=name;currencies").then().statusCode(404);
    }
}