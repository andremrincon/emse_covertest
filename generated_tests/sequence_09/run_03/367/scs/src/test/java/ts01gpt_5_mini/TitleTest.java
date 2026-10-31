package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleMatchingTitleReturns200() {
        String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("baseUrl", "http://localhost:8080"));
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/{sex}/{title}", "male", "mr");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleNonMatchingTitleReturns200() {
        String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("baseUrl", "http://localhost:8080"));
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/{sex}/{title}", "male", "unknown-title-" + System.currentTimeMillis());
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMatchingTitleReturns200() {
        String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("baseUrl", "http://localhost:8080"));
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/{sex}/{title}", "female", "mrs");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMatchingTitleReturns200() {
        String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("baseUrl", "http://localhost:8080"));
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/{sex}/{title}", "none", "prof");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturns200() {
        String base = System.getenv().getOrDefault("BASE_URL", System.getProperty("baseUrl", "http://localhost:8080"));
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/title/{sex}/{title}", "other", "noone");
        resp.then().statusCode(200);
    }
}