package ts01gpt_5_mini;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
public class TitleTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }
    @Test(timeout = 60000)
    public void testMaleValidTitleMrReturnsOne() {
        given().when().get("/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        act.then().body(equalTo("1"));
    }
    @Test(timeout = 60000)
    public void testFemaleUppercaseDrReturnsZero() {
        given().when().get("/api/pat/{txt}", "arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FEMALE", "DR");
        act.then().body(equalTo("0"));
    }
    @Test(timeout = 60000)
    public void testNoneWithProfReturnsTwo() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "prof");
        act.then().body(equalTo("2"));
    }
    @Test(timeout = 60000)
    public void testUnrecognizedSexReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "unknown", "anything");
        act.then().body(equalTo("-1"));
    }
    @Test(timeout = 60000)
    public void testMaleWithInvalidTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        act.then().body(equalTo("-1"));
    }
}