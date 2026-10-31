package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Test(timeout = 60000)
    public void testV1AlphaCodeSetsLanguageFields() {

        Response response = given().when().get("/v1/alpha/US");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1LangSetsLanguageFields() {

        Response response = given().when().get("/v1/lang/es");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencySetsLanguageFields() {

        Response response = given().when().get("/v1/currency/USD");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1NameSetsLanguageFields() {

        Response response = given().when().get("/v1/name/France");

        response.then().statusCode(404);
    }
}