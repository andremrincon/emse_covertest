package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_notypevar_triggers_sum_branch_and_final_override_i28() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "keep");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_triggers_hello7_branch_i7() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "anything");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_triggers_compareto_branch_result_two() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_notypevar_default_result_zero_when_no_conditions_met() {
        String rnd = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", rnd).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        resp.then().body(equalTo("0"));
    }
}