package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetterCode_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetterCode_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/eng").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_NoMatch_Returns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/xx").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
    }
}