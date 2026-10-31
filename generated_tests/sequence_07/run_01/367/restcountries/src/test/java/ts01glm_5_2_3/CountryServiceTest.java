package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_AcronymMatch() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/EU").then().statusCode(200).body(notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA_AcronymMatch() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/NAFTA").then().statusCode(200).body(notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NoMatch_Returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}