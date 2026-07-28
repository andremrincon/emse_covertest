package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    public void testV2Lang_TwoLetterCode_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/lang/es");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2Lang_ThreeLetterCode_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/lang/eng");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2RegionalBloc_CaseInsensitiveAcronym_Returns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/eu");
        resp.then().statusCode(200);
    }
}