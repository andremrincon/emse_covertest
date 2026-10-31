package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeCharCodeV1() {
        given().when().get("/v1/lang/spa").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharCodeV1() {
        given().when().get("/v1/lang/es").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageV2Spanish() {
        given().when().get("/v2/lang/Spanish").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}