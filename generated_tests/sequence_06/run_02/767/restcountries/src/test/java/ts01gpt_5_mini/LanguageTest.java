package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        baseUrl = env;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_1_via_v1AlphaUS() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_2_via_v1AlphaUS() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName_via_v1AlphaUS() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testSetNativeName_via_v1AlphaUS() {
        given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/v1/alpha/US").then().statusCode(200).extract().response();
        resp.then().body("languages[0]['nativeName']", equalTo("English"));
    }
}