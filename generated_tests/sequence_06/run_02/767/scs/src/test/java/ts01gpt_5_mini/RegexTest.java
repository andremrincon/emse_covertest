package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlBranch() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "http://a/b");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateBranch() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "1", "example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "mon12jan");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeBranch() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "12.34e+56");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneBranch() {
        given().when().get("/api/title/{sex}/{title}", "male", "Smith").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/{txt}", "foobar");
        act.then().statusCode(200);
    }
}