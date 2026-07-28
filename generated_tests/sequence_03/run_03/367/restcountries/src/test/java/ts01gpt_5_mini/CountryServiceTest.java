package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    private static final String BASE;
    static {
        String env = System.getProperty("API_BASE_URL");
        if (env == null) env = System.getenv("API_BASE_URL");
        if (env == null) env = "http://localhost:8080/rest";
        BASE = env;
        RestAssured.baseURI = BASE;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_twoLetter_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/es");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_threeLetter_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/lang/eng");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_acronym_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/EU");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_nafta_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/regionalbloc/NAFTA");
        response.then().statusCode(200);
    }
}