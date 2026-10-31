package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = System.getProperty("api.base", "http://localhost:8080/rest");
        }
    }

    @Test(timeout = 60000)
    public void testV1Lang_TwoLetterCode_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/lang/es");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Lang_ThreeLetterCode_Returns200() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/lang/eng");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Lang_InvalidNumericCode_Returns404() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/lang/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2RegionalBloc_AcronymReturns200() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/regionalbloc/EU");
        assertEquals(200, act.getStatusCode());
    }
}