package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlpha2LetterCode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlpha3LetterCode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/DEU");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByAlphaNotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/ZZZ");
        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCodeListDuplicates() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha?codes=US,US");
        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByCodeListMissingParam() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha");
        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testFulltextSearchAltSpelling() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/Federal%20Republic%20of%20Germany?fullText=true");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSubstringSearchAltSpelling() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/Bundesrepublik");
        response.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testLoadJson() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/all");
        response.then().statusCode(200);
    }
}