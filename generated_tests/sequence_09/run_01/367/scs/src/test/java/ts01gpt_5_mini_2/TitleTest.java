package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitleReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUppercaseTitleHandledAndReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "MALE", "DR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "miss");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitleReturns200() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnmatchedTitleProducesResponse() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        resp.then().statusCode(200);
    }
}