package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleTitleNoMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FeMale", "Mrs");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleNoMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "mr");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneTitleMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "Dr");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneTitleNoMatch() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "sir");
        act.then().body(equalTo("-1"));
    }
}