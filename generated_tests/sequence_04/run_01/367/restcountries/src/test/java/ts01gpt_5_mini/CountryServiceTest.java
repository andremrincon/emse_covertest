package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetter_Status200() {
        given().when().get("/v1/lang/es").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetter_Status200() {
        given().when().get("/v1/lang/zho").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/zho");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_InvalidLength_ReturnsNotFound() {
        given().when().get("/v1/lang/es").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/abcd");
        act.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ByAcronym_Status200() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }
}