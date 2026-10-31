package ts01gpt_5_mini_3;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080/rest";
        }
        BASE = cfg;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocReturns200ForEU() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/{regionalbloc}", "EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocReturns404ForNumeric() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/{regionalbloc}", "123");
        act.then().statusCode(404);
    }
}