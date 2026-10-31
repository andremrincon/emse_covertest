package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithFullNameSpanish() {
        given().when().get("/v2/lang/Spanish").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithFullNameEnglish() {
        given().when().get("/v2/lang/English").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWith3CharCode() {
        given().when().get("/v2/lang/eng").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWith2CharCode() {
        given().when().get("/v2/lang/es").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(404);
    }
}