package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getByLanguage_twoCharCode_returnsCountries() {
        given().when().get("/v2/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_threeCharCode_returnsCountries() {
        given().when().get("/v2/lang/spa").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByLanguage_nonMatchingCode_returnsNotFound() {
        given().when().get("/v2/lang/zz").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_validAcronym_returnsCountries() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getByRegionalBloc_nonMatchingAcronym_returnsNotFound() {
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}