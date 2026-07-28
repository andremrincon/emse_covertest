package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharCodeMatch() {
        given().when().get("/v2/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeCharCodeMatch() {
        given().when().get("/v2/lang/spa").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharCodeNoMatch() {
        given().when().get("/v2/lang/xx").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocAcronymMatch() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocCaseInsensitive() {
        given().when().get("/v2/regionalbloc/eu").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNoMatch() {
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}