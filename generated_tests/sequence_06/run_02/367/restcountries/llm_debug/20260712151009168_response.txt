package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBloc_EU_returns200() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBloc_invalidNumeric_returns404() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/{regionalbloc}", "123");
        act.then().statusCode(404);
    }
}