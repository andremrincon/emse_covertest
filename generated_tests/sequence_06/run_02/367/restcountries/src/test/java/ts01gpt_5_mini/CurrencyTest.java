package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

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
    public void testV1AlphaUSContainsUSD() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("currencies[0]", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSDStatus200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEURContainsEuroName() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR");
        resp.then().body(containsString("Euro"));
    }

    @Test(timeout = 60000)
    public void testV1AllContainsEuroSymbol() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        resp.then().body(containsString("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaUSStatus200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/alpha/US");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1NameFranceContainsEuro() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France");
        resp.then().body(containsString("Euro"));
    }
}