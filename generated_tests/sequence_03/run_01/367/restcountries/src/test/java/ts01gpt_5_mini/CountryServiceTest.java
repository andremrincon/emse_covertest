package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_twoLetter_shouldReturn200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_threeLetter_shouldReturn200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/eng");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_acronymMatch_shouldReturn200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_otherAcronymMatch_shouldReturn200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/NAFTA");
        assertEquals(200, resp.getStatusCode());
    }
}