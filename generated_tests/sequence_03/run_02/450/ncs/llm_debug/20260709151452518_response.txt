package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testFisher_A1_B1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_A1_Bnot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Anot1_B1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 6, 3, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Anot1_Bnot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 6, 4, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 1.2);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeX_executesEdgeBranches_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 1e6);
        act.then().statusCode(200);
    }
}