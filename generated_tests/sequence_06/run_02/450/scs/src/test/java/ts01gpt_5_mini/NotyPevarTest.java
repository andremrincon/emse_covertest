package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

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
    public void testNotyPevar_returns3_when_i28_triggers_lastBranch() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_status200_for_i7_executes_middleBranches() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", "A", uuid, "B").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "zzz").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returns2_when_i5_and_s_greater_than_hello() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 5, "world").then().body(equalTo("2"));
    }
}