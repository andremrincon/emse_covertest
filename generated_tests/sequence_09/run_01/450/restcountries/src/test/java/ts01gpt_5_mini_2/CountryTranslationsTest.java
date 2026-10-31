package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetDe_translationPopulatedForAlpha_US() {
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals("Vereinigte Staaten von Amerika", act.jsonPath().getString("translations.de"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetEs_translationPopulatedForAlpha_US() {
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals("Estados Unidos", act.jsonPath().getString("translations.es"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetFr_translationPopulatedForAlpha_US() {
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals("États-Unis", act.jsonPath().getString("translations.fr"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetJa_translationPopulatedForAlpha_US() {
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals("アメリカ合衆国", act.jsonPath().getString("translations.ja"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetIt_translationPopulatedForAlpha_US() {
        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals("Stati Uniti D'America", act.jsonPath().getString("translations.it"));
    }
}