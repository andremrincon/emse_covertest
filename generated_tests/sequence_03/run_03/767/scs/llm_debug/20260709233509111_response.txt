package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

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
    public void testMaleMrReturns1() {
        String seed = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "Male", "MR");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMsReturns0() {
        String seed = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FeMaLe", "Ms");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneDrReturns2() {
        String seed = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "NoNe", "Dr");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        String seed = "arrange-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", seed).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "other", "Dr");
        act.then().body(equalTo("-1"));
    }
}