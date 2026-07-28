package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{regionalbloc}", "EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Invalid_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{regionalbloc}", "123").then().statusCode(404);
    }
}