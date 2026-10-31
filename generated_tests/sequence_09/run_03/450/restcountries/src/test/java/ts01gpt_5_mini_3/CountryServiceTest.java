package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_returns200() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA_returns200() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "NAFTA");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_InvalidNumeric_returns404() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        String unique = "123";
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", unique);
        assertEquals(404, act.getStatusCode());
    }
}