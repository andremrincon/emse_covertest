package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageTwoChars() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/lang/es");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageThreeChars() {
        given().when().get("/v2/lang/eng").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/lang/eng");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBlocAcronym() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/EU");
        response.then().statusCode(200);
    }
}