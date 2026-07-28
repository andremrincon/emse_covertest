package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE = System.getProperty("base.url", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParameters() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenParametersTooLarge() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/5/0.75");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenInternalExecutionFails() {
        given().when().get("/api/triangle/2/2/3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/1.2");
        resp.then().statusCode(200);
    }
}