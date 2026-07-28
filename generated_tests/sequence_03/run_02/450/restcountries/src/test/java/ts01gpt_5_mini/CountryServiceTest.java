package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryServiceTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Iso639_1_returns200() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/lang/es");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_Iso639_2_returns200() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/lang/spa");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_invalidLength_returns404() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/lang/1234");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_returns200() {
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v2/regionalbloc/EU");
        assertEquals(200, resp.getStatusCode());
    }
}