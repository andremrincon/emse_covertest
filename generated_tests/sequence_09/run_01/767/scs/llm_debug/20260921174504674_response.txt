package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns3_when_i_is_28() {
        given().when().get("/api/notypevar/{i}/{s}", 7, "ignored").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().statusCode(200).body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns2_when_i_is_5_and_s_greater_than_hello() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "world");
        resp.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns0_when_i_is_1_and_s_less_than_hello() {
        given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 1, "a");
        resp.then().statusCode(200).body(equalTo("0"));
    }
}