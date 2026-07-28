package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ValidAcronym() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidAcronym() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_AnotherValidAcronym() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(200);
    }
}