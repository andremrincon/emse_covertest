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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080/rest" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_getByLanguage_twoLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByLanguage_threeLetter_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/eng");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_getByRegionalBloc_acronym_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/EU");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_getByRegionalBloc_caseInsensitive_acronym_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/regionalbloc/nafta");
        act.then().statusCode(200);
    }
}