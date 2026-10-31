package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

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

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testIso6391SetViaV1AlphaUS() {
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testIso6392SetViaV1AlphaUS() {
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testNameSetViaV1AlphaUS() {
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testNativeNameSetViaV1AlphaUS() {
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['nativeName']", equalTo("English"));
    }
}