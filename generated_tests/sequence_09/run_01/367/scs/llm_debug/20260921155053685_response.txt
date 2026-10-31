package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String cfg = Optional.ofNullable(System.getProperty("base.url")).orElse(System.getenv("BASE_URL"));
        RestAssured.baseURI = cfg != null ? cfg : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithUnknownReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "unknown-title");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithUnknownReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "no-title");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithDrReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithUnknownReturnsOk() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "xyz");
        act.then().statusCode(200);
    }
}