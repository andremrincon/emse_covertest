package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {
    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        BASE = env;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testNotypevar_compareToBranch_returns2() {
        given().when().get("/api/notypevar/{i}/{s}", 7, "irrelevant").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 2, "z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_noBranches_returns0() {
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotypevar_sumAndGreaterThan_returns3() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().body(equalTo("3"));
    }
}