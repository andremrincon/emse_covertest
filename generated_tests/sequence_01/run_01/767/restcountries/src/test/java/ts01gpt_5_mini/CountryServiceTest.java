package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE_URL");
        base = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_EU_Returns200() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_NAFTA_Returns200() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/NAFTA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_Invalid_Returns404() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/123");
        resp.then().statusCode(404);
    }
}