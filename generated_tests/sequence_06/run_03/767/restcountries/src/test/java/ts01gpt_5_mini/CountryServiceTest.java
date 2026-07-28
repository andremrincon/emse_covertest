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
        String fromProp = System.getProperty("rest.baseurl");
        String fromEnv = System.getenv("REST_BASE_URL");
        RestAssured.baseURI = fromProp != null && !fromProp.isEmpty() ? fromProp : (fromEnv != null && !fromEnv.isEmpty() ? fromEnv : "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetter() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "es");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetter() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/{lang}", "eng");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ByAcronym() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/{regionalbloc}", "NAFTA");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_ByOtherAcronym() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/{regionalbloc}", "EU");
        resp.then().statusCode(200);
    }
}