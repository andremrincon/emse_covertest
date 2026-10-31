package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testSetCodeViaV1Alpha() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetNameViaV1Alpha() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetSymbolViaV1Currency() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetSymbolViaV2Currency() {
        given().when().get("/v2/currency/EUR").then().statusCode(404);
    }
}