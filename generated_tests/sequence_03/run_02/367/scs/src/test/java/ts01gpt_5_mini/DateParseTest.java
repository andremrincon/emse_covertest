package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("TEST_BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("TEST_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = System.getProperty("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testDayMonJanProducesTwo() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "mon", "jan").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownDayWithAugProducesEight() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "noday", "aug").then().body(equalTo("8"));
    }

    @Test(timeout = 60000)
    public void testUppercaseSunWithDecProducesThirteen() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "SUN", "DEC").then().body(equalTo("13"));
    }

    @Test(timeout = 60000)
    public void testThurMixedCaseWithMarProducesFour() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "ThUr", "mar").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testTueWithMayProducesSix() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "tue", "may").then().body(equalTo("6"));
    }

    @Test(timeout = 60000)
    public void testMonWithInvalidMonthProducesOne() {
        String token = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", token).then().statusCode(lessThan(300));
        given().when().get("/api/dateparse/{day}/{month}", "mon", "movember").then().body(equalTo("1"));
    }
}