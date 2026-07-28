package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        if (prop != null && !prop.isEmpty()) {
            base = prop;
        } else if (env != null && !env.isEmpty()) {
            base = env;
        } else {
            base = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testRegionalBlocReturns200ForEU() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/EU");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBlocReturns404ForInvalidCode() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/123");
        assertEquals(404, resp.getStatusCode());
    }
}