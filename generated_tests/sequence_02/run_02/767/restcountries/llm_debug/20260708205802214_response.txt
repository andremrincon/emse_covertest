package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetter_Returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetter_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/eng").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_InvalidLength_Returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v2/lang/abcd").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_AcronymCaseInsensitive_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/eu").then().statusCode(200);
    }
}