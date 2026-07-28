package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void returnsZeroFor_i0_and_sLessOrEqualHello() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 0, "a");
        act.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void returnsTwoWhen_i5_and_sGreaterThanHello() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 5, "world");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void returnsThreeWhen_iGreaterThanFive() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        act.then().assertThat().body(equalTo("3"));
    }
}