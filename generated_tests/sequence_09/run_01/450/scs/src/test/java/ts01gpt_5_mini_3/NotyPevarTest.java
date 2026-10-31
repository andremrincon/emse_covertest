package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_branch_sumEquals56_executes_and_returns200() {
        given().when().get("/api/pat/{txt}", "The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 28, "z");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_branch_helloConcatenation_executes_and_returns200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "zzz");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_branch_compareTo_executes_and_returns200() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 0, "z");
        act.then().statusCode(200);
    }
}