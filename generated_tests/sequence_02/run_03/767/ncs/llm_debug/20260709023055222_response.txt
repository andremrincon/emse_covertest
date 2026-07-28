package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParameters() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenParametersTooLarge() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1001/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns200ForNormalValues() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400ForOutOfRangeValues() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/10001/1");
        act.then().statusCode(400);
    }
}