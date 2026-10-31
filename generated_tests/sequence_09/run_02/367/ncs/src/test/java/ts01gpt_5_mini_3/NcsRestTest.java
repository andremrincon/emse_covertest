package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String envUrl = System.getProperty("baseUrl");
        if (envUrl == null || envUrl.isEmpty()) {
            envUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        }
        RestAssured.baseURI = envUrl;
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_WhenMExceedsLimit() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1001/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns200_WhenValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_Returns400_WhenAExceedsLimit() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/10001/5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_Returns200_WhenValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5");
        act.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/2/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_Valid_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/2.5");
        act.then().statusCode(200);
    }
}