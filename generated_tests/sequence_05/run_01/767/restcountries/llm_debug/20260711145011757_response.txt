package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_status200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalid_alphacode_returns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_status200() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Currency_unknown_returns404() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_bodyCurrencyNameEuro() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR");
        resp.then().body("[0].currencies[0].name", equalTo("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2Name_fullTextTrue_alpha2CodeDE() {
        given().when().get("/v2/name/Germany").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/name/Germany?fullText=true");
        resp.then().body("[0].alpha2Code", equalTo("DE"));
    }
}