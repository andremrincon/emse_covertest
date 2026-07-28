package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_validAcronym_returnsCountries() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200).body("size()", greaterThan(0));
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_notFound_returns404() {
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_naftaAcronym_returnsCountries() {
        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(200).body("size()", greaterThan(0));
    }
}