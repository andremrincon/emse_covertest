package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String fromEnv = System.getenv("API_BASE");
            if (fromEnv == null || fromEnv.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080/rest";
            } else {
                RestAssured.baseURI = fromEnv;
            }
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testSetDeViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals("Vereinigte Staaten von Amerika", resp.jsonPath().getString("translations.de"));
    }

    @Test(timeout = 60000)
    public void testSetEsViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals("Estados Unidos", resp.jsonPath().getString("translations.es"));
    }

    @Test(timeout = 60000)
    public void testSetFrViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals("États-Unis", resp.jsonPath().getString("translations.fr"));
    }

    @Test(timeout = 60000)
    public void testSetJaViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals("アメリカ合衆国", resp.jsonPath().getString("translations.ja"));
    }

    @Test(timeout = 60000)
    public void testSetItViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertEquals("Stati Uniti D'America", resp.jsonPath().getString("translations.it"));
    }
}