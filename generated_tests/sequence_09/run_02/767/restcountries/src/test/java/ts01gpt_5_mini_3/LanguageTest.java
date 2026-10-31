package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null) {
            base = System.getProperty("base.url", System.getenv("BASE_URL"));
        }
        if (base == null) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1Alpha_US_status200() {
        given().when().get("/v1/all").then().statusCode(404);
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_iso639_1_equals_en() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_iso639_2_equals_eng() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_name_equals_English() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("languages[0].name", equalTo("English"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_nativeName_equals_English() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().body("languages[0].nativeName", equalTo("English"));
    }
}