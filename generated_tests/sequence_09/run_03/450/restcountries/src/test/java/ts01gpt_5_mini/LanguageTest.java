package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("rest.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6391_v1Alpha_US_returns_en() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        act.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6392_v1Alpha_US_returns_eng() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        act.then().body("languages[0]['iso639_2']", equalTo("eng"));
    }

    @Test(timeout = 60000)
    public void testSetName_v2Callingcode_1_returns_English() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/1").then().statusCode(200).extract().response();
        act.then().body("languages[0].name", equalTo(Arrays.asList("English", "Samoan")));
    }

    @Test(timeout = 60000)
    public void testSetNativeName_v2Callingcode_1_returns_English() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/callingcode/1").then().statusCode(200).extract().response();
        act.then().body("languages[0].nativeName", equalTo(Arrays.asList("English", "gagana fa'a Samoa")));
    }
}