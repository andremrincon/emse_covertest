package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String cfg = System.getProperty("test.server");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("TEST_SERVER");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void test_sumEquals56_branch_executed_returns_overriddenByYGreaterThan5() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/a");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_concatHelloY_branch_executed_status200() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/7/a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_compareTo_branch_sets_result_two() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/1/z");
        assertEquals("2", resp.getBody().asString());
    }
}