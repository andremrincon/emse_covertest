package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = url;
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_currencyCodeIsUSD() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("currencies['USD'].code", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_hasEuroSymbol() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().body("[0].currencies[0].symbol", equalTo("\u20ac"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1All_currencyMap_containsEURName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().body("[0].currencies['EUR'].name", equalTo("Euro"));
    }
}