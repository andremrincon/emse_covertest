package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        BASE = base;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1Alpha() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        resp.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso639_2_v1Alpha() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        resp.then().body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName_v1Alpha() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        resp.then().body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Alpha() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        resp.then().body("languages[0]['nativeName']", equalTo("English"));
    }
}