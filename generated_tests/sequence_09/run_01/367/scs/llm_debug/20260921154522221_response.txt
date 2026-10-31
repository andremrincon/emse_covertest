package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotypevarReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 5, "a");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotypevarReturnsTwoWhenCompareGreater() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 0, "z");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotypevarSum56PathExecutedLeadsToThree() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotypevarExecutesConcatBranchAndReturnsOkStatus() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "m");
        resp.then().statusCode(200);
    }
}