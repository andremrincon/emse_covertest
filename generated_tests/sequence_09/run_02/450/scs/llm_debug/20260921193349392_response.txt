package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_returns3_and_triggers_hello7_in_arrange() {
        given().when().get("/api/notypevar/{i}/{s}", 7, "a").then().statusCode(lessThan(300));
        String body = given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().extract().asString();
        assertEquals("3", body);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2_with_s_greater_than_hello_returns2() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        String body = given().when().get("/api/notypevar/{i}/{s}", 2, "z").then().extract().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i1_with_s_not_greater_returns0() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        String body = given().when().get("/api/notypevar/{i}/{s}", 1, "a").then().extract().asString();
        assertEquals("0", body);
    }
}