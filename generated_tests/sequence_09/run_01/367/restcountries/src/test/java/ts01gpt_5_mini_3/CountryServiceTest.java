package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRegionalBlocAcronymMatch() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testRegionalBlocNaftaMatch() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/NAFTA");
        resp.then().statusCode(200);
    }
}