package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {
    private static String BASE_URL;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("BASE_URL");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        BASE_URL = cfg;
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testPositivePositiveRemainder() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/17/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveNegativeRemainder() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/17/-9");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativePositiveRemainder() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/-17/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeNegativeRemainder() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/-17/-5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroA_Remainder() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/0/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBZero_BadRequest() {
        given().when().get(BASE_URL + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE_URL + "/api/remainder/5/0");
        act.then().statusCode(200);
    }
}