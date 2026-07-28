package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}