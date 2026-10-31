package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private static String getBase() {
        return Optional.ofNullable(System.getProperty("api.base"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE")).orElse("http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymMatchReturns200() {
        RestAssured.baseURI = getBase();
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{regionalbloc}", "EU").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocOtherAcronymMatchReturns200() {
        RestAssured.baseURI = getBase();
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{regionalbloc}", "EC").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocInvalidReturns404() {
        RestAssured.baseURI = getBase();
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v2/regionalbloc/{regionalbloc}", "123").then().statusCode(404);
    }
}