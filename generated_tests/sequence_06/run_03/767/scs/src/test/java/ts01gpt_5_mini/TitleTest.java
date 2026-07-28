package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleMrReturns1() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleUnknownTitleReturnsMinusOne() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "unknown-title-" + UUID.randomUUID().toString());
        resp.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleDrReturns0() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "dr");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneProfReturns2() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "alien", "dr");
        resp.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testCaseInsensitivityReturnsSameAsLowercase() {
        String ping = "ping-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", ping).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "MALE", "DR");
        resp.then().body(equalTo("1"));
    }
}