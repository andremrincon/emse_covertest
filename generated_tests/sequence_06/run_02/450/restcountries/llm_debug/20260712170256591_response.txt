package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CountryServiceTest {

    private static String baseUrl() {
        return Optional.ofNullable(System.getProperty("api.base"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE"))
                        .orElse("http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testRegionalBloc_AcronymMatch_CaseInsensitive_returns200() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/regionalbloc/eu");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBloc_NotFound_returns404() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/regionalbloc/123");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegionalBloc_NAFTA_responseBodyContainsAcronym() {
        String base = baseUrl();
        given().when().get(base + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/v2/regionalbloc/NAFTA");
        String body = act.getBody().asString();
        assertTrue(body.contains("NAFTA"));
    }
}