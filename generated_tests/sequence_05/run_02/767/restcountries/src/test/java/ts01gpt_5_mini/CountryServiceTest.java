package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymReturns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNaftaContainsUnitedStatesInBody() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/NAFTA").then().body(containsString("United States"));
    }

    @Test(timeout = 60000)
    public void testRegionalBlocCaseInsensitiveAcronym() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/eu");
        assertEquals(200, resp.getStatusCode());
    }
}