package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        BASE = env;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1_returns200() {
        RestAssured.given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get(BASE + "/api/fisher/5/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1_returns200() {
        RestAssured.given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get(BASE + "/api/fisher/5/4/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1_returns200() {
        RestAssured.given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get(BASE + "/api/fisher/6/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_neitherAorBIs1_returns200() {
        RestAssured.given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get(BASE + "/api/fisher/6/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeX_executionPath_returns200() {
        RestAssured.given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get(BASE + "/api/fisher/20/4/1000000.0").then().statusCode(200);
    }
}