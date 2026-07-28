package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetCodeFromV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().body("[0].currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testSetNameFromV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().body("[0].currencies[0].name", equalTo("Euro"));
    }

    @Test(timeout = 60000)
    public void testSetSymbolFromV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300));
        given().when().get("/v2/currency/EUR").then().body("[0].currencies[0].symbol", equalTo("\u20ac"));
    }
}