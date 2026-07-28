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
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsTwo_when_i3_and_s_zoo() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 3, "zoo");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsThree_when_i28_and_s_a() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        resp.then().body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_returnsZero_when_i1_and_s_a() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 1, "a");
        resp.then().body(equalTo("0"));
    }
}