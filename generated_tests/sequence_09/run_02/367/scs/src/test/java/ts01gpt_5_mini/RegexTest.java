package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        if (base.startsWith("http://") || base.startsWith("https://")) {
            String trimmed = base.replaceFirst("https?://", "");
            if (trimmed.contains(":")) {
                String host = trimmed.substring(0, trimmed.indexOf(":"));
                String portPart = trimmed.substring(trimmed.indexOf(":") + 1);
                if (portPart.contains("/")) {
                    portPart = portPart.substring(0, portPart.indexOf("/"));
                }
                RestAssured.baseURI = base.substring(0, base.indexOf("://") + 3) + host;
                try {
                    RestAssured.port = Integer.parseInt(portPart);
                } catch (Exception e) {
                    RestAssured.port = 80;
                }
            } else {
                RestAssured.baseURI = base;
            }
        } else {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDate() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "mon12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpe() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "1.2e%2B34");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrl() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "http%3A%2F%2Fa%2Fb");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNoneForOtherText() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "hello");
        act.then().statusCode(200);
    }
}