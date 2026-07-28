package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/1/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/2/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/2/1/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeLoops_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/11/9/0.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/abc/5/0.5");
        act.then().statusCode(400);
    }
}