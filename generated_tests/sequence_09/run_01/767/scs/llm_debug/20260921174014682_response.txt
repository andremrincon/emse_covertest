package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class DateParseTest {

    private String baseUri() {
        String b = System.getProperty("base.url", System.getenv("BASE_URL"));
        return (b == null || b.isEmpty()) ? "http://localhost:8080" : b;
    }

    @Test(timeout = 60000)
    public void testDayDetectedWithJanProducesTwo() {
        String base = baseUri();
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tuesday", "Feb").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Mar").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tuesday", "Apr").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Mon", "Jan").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoDayWithMarProducesThree() {
        String base = baseUri();
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tuesday", "May").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Jun").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Jul").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Mar").then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testThurDetectedWithDecProducesThirteen() {
        String base = baseUri();
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tuesday", "Aug").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Sep").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tue", "Oct").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "thur", "DEC").then().body(equalTo("13"));
    }

    @Test(timeout = 60000)
    public void testNoDayWithMayProducesFive() {
        String base = baseUri();
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "tuesday", "Nov").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "Wednesday", "Dec").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "sun", "jul").then().statusCode(lessThan(300));
        given().baseUri(base).when().get("/api/dateparse/{d}/{m}", "unknown-day", "May").then().body(equalTo("5"));
    }
}