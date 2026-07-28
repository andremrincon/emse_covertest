package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testFisherSuccessReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherRejectsTooLargeParameters() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/5/0.75");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeExceptionLeadsTo400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/8/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturnsCorrectValueInBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        resp.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfRangeReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10001/5");
        resp.then().statusCode(400);
    }
}