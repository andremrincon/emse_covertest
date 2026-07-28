package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_EU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_NAFTA_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/NAFTA");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetRegionalBloc_unknown_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/ZZZ");
        act.then().statusCode(404);
    }
}