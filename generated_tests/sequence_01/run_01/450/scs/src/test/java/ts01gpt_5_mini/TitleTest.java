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
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleReturnsOne() {
        String healthText = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthText).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleReturnsZero() {
        String healthText = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthText).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneSexTitleReturnsTwo() {
        String healthText = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthText).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownCombinationReturnsMinusOne() {
        String healthText = "health-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", healthText).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "other", "unknown");
        resp.then().body(equalTo("-1"));
    }
}