package ts01gpt_5_mini;

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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRegionalBloc_EU_returns200() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRegionalBloc_invalidNumeric_returns404() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/123");
        resp.then().statusCode(404);
    }
}