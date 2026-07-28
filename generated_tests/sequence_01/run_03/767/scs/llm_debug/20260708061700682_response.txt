package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.RestAssured;
import java.util.UUID;

public class NotyPevarTest {

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
    public void test_notypevar_invokesConstructor_and_yGreaterThanX_returns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "alpha");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_executes_hello7_branch_returns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "setup", marker, "run").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_notypevar_compareTo_branch_returns2_in_body() {
        String marker = UUID.randomUUID().toString();
        given().when().get("/api/costfuns/{i}/{s}", 1, marker).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "z");
        assertEquals("2", resp.getBody().asString());
    }
}