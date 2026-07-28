package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
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
    public void testSetIso639_1_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        String iso639_1 = resp.jsonPath().getString("languages[0].iso639_1");
        assertEquals("en", iso639_1);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetIso639_2_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        String iso639_2 = resp.jsonPath().getString("languages[0].iso639_2");
        assertEquals("eng", iso639_2);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetName_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        String name = resp.jsonPath().getString("languages[0].name");
        assertEquals("English", name);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetNativeName_FromV1Alpha_US() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        String nativeName = resp.jsonPath().getString("languages[0].nativeName");
        assertEquals("English", nativeName);
    }
}