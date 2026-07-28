package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String envBase = System.getProperty("baseUrl");
        if (envBase == null || envBase.isEmpty()) {
            String env = System.getenv("BASE_URL");
            RestAssured.baseURI = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        } else {
            RestAssured.baseURI = envBase;
        }
    }

    @Test(timeout = 60000)
    public void returnsZeroWhenNoConditionsMet() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "a");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void returnsTwoWhenCompareToProducesNegative() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "zzz");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void returnsThreeWhenYGreaterThanFive() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 6, "a");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void executesXPlusYEquals56Branch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "algorithm");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void executesHelloConcatEqualsHello7Branch() {
        given().when().get("/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", resp.getBody().asString());
    }
}