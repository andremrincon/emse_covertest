package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testTranslationsViaAlphaCode() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaName() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/France");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCurrency() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCapital() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/capital/London");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaRegion() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/region/Europe");
        response.then().statusCode(200);
    }
}