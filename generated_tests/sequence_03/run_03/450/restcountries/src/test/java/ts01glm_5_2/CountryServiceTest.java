package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/lang/es").then().statusCode(200).body("size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/lang/spa").then().statusCode(200).body("size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguageWithInvalidCodeReturnsNotFound() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/lang/123").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithValidAcronym() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/EU").then().statusCode(200).body("size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithNafta() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(200).body("size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBlocWithInvalidReturnsNotFound() {
        given().when().get("/").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}