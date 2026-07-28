package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_returnsThree() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_triggersHello7_branch_and_returnsThree() {
        given().when().get("/api/pat/{txt}", "setup2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "hello");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i5_returnsTwo() {
        given().when().get("/api/pat/{txt}", "setup3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "z");
        resp.then().body(equalTo("2"));
    }
}