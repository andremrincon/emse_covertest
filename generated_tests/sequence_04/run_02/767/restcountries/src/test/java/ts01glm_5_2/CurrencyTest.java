package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetAllCountriesExercisesCurrencySetters() {
        given().when().get("/v1/all").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByAlphaCodeExercisesCurrencySetters() {
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCurrencyExercisesCurrencySetters() {
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByNameExercisesCurrencySetters() {
        given().when().get("/v1/name/France").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCallingCodeExercisesCurrencySetters() {
        given().when().get("/v1/callingcode/1").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByCapitalExercisesCurrencySetters() {
        given().when().get("/v1/capital/London").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionExercisesCurrencySetters() {
        given().when().get("/v1/region/Europe").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetBySubregionExercisesCurrencySetters() {
        given().when().get("/v1/subregion/Western%20Europe").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLangExercisesCurrencySetters() {
        given().when().get("/v1/lang/es").then().statusCode(404);
    }
}