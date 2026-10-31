package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        BASE = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080/rest");
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_iso2_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_iso3_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/eng").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_invalidNumeric_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_acronym_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_unknown_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/123").then().statusCode(404);
    }
}