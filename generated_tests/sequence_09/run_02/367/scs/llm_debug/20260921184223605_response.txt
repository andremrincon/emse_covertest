package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotypevar_branchCombinationA() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 28, "a").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotypevar_branchCombinationB() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 7, "world").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotypevar_branchCombinationC() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/notypevar/{i}/{s}", 3, "zzz").then().statusCode(200);
    }
}