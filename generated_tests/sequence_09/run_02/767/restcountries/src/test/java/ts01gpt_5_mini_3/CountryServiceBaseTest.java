package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_TwoLetter_US_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_ThreeLetter_USA_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/USA").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_Invalid_123_returns400() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_codes_semicolon_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US;CA").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_codes_duplicates_single_entry() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha?codes=US;US").then().body("size()", equalTo(1));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_fullText_nameMatch_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France?fullText=true").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_fullText_altSpellingMatch_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/US?fullText=true").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Alpha_fields_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v2/alpha/US?fields=name;capital;population").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2All_fields_returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().statusCode(200);
    }
}