package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        base = env;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1_withLoop_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/1/101/1000000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_returns200() {
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/1/2/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_returns200() {
        given().when().get(base + "/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/2/1/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_bNot1_returns200() {
        given().when().get(base + "/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/4/4/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_possibleOverflowPath_returns200() {
        given().when().get(base + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/1/101/1000000000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_possibleNegativePath_returns200() {
        given().when().get(base + "/api/expint/1/0.1").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/100/2/1000000.0");
        resp.then().statusCode(200);
    }
}