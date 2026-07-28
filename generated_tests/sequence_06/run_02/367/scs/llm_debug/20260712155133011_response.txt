package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            env = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_branchB_and_D_are_true_returns3_for_i7() {
        given().when().get("/api/pat/{txt}", "health-check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_branchA_and_D_are_true_returns3_for_i28() {
        given().when().get("/api/pat/{txt}", "arrange-unique-1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        act.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_branchC_true_and_D_false_returns2_for_i1_s_z() {
        given().when().get("/api/pat/{txt}", "arrange-unique-2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 1, "z");
        act.then().body(equalTo("2"));
    }
}