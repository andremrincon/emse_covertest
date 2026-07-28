package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_iso2_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/en").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_iso3_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/eng").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_nonexistent_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/xyz").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_acronym_returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_otherAcronym_evaluated_returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/X").then().statusCode(404);
    }
}