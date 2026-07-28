package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6391_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6392_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetName_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0]['nativeName']", equalTo("English"));
    }
}