package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Objects;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private String resolveBase() {
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) return env;
        String prop = System.getProperty("api.base");
        if (prop != null && !prop.isEmpty()) return prop;
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymReturns200() {
        String base = resolveBase();
        RestAssured.baseURI = base;
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocCaseInsensitiveAcronymReturns200() {
        String base = resolveBase();
        RestAssured.baseURI = base;
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/nafta");
        act.then().statusCode(200);
    }
}