package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void maleWithMrReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        r.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void maleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "male", "unknown");
        r.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void femaleWithMrsReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        r.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void femaleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "female", "someone");
        r.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void noneWithDrReturnsTwo() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        r.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void noneWithOtherReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/title/{sex}/{title}", "none", "citizen");
        r.then().body(equalTo("-1"));
    }
}