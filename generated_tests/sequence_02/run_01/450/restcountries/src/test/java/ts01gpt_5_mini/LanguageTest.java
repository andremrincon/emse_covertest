package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env != null && !env.isEmpty()) ? env : "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testIso639_1IsExposedForAlphaCodeUS() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().asString();
        assertTrue(resp.contains("\"iso639_1\"") || resp.contains("iso639_1"));
        assertTrue(resp.contains("\"en\"") || resp.contains("en"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testIso639_2IsExposedForAlphaCodeUS() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().asString();
        assertTrue(resp.contains("\"iso639_2\"") || resp.contains("iso639_2"));
        assertTrue(resp.contains("\"eng\"") || resp.contains("eng"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNameIsExposedForAlphaCodeUS() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().asString();
        assertTrue(resp.contains("\"name\"") || resp.contains("name"));
        assertTrue(resp.contains("English"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNativeNameIsExposedForAlphaCodeUS() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().asString();
        assertTrue(resp.contains("\"nativeName\"") || resp.contains("nativeName"));
        assertTrue(resp.contains("English"));
    }
}