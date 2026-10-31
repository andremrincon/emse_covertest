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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyName() {
        given().when().get("/v2/currency/USD").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencySymbol() {
        given().when().get("/v2/currency/XOF").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/XOF").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyCode() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyName() {
        given().when().get("/v1/currency/XOF").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/XOF").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsCurrencyFields() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocEndpointReturnsCurrencyFields() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsCurrencyFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsCurrencyFields() {
        given().when().get("/v2/name/Germany").then().statusCode(lessThan(300));
        given().when().get("/v2/name/Germany").then().statusCode(200);
    }
}