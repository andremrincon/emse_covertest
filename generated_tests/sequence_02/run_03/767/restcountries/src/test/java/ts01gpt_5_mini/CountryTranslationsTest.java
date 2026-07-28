package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testSetDeViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEsViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFrViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJaViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetItViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}