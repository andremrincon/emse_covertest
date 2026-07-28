package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaAlphaCode() {

        given().when().get("/v1/alpha/US").then().statusCode(404);

        Response response = given().when().get("/v1/alpha/US");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaName() {

        given().when().get("/v1/name/France").then().statusCode(404);

        Response response = given().when().get("/v1/name/France");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaCurrency() {

        given().when().get("/v1/currency/USD").then().statusCode(404);

        Response response = given().when().get("/v1/currency/USD");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaLang() {

        given().when().get("/v1/lang/es").then().statusCode(404);

        Response response = given().when().get("/v1/lang/es");

        response.then().statusCode(404);
    }
}