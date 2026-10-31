package ts01gpt_5_mini_2;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.Objects;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {
    private final String base;

    public NotyPevarTest() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("baseUrl", "http://localhost:8080");
        }
        this.base = env;
    }

    @Test(timeout = 60000)
    public void testNotypevarReturnsZero() {
        int i = 5;
        String s = "example-string";
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/{i}/{s}", i, s);
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotypevarReturnsTwo() {
        int i = 5;
        String s = "z";
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/{i}/{s}", i, s);
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevarReturnsThree() {
        int i = 7;
        String s = "a";
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/notypevar/{i}/{s}", i, s);
        resp.then().body(equalTo("3"));
    }
}