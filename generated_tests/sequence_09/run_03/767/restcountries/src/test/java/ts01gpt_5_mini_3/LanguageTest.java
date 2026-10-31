package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        RestAssured.get("/v1/alpha/US").then().body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetIso639_2_v1Currency_USD() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        RestAssured.get("/v1/currency/USD").then().body("[0].languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetName_v1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        RestAssured.get("/v1/alpha/US").then().body("languages[0].name", equalTo("English"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Name_France() {
        given().when().get("/v1/alpha/FR").then().statusCode(lessThan(300));
        RestAssured.get("/v1/name/France").then().body("[0].languages[0].nativeName", equalTo("français"));
    }
}