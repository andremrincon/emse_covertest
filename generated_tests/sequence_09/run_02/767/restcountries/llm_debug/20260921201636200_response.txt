package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    private static final String BASE;
    static {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("base.url", "http://localhost:8080/rest");
        }
    }

    @Test(timeout = 60000)
    public void testRegionalBlocFoundByAcronym_EU() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/v2/regionalbloc/EU");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBlocFoundByAcronym_NAFTA() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/v2/regionalbloc/NAFTA");
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNotFound_Numeric() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response response = given().when().get(BASE + "/v2/regionalbloc/123");
        assertEquals(404, response.getStatusCode());
    }
}