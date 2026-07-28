package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
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
    public void testMaleTitleMatchReturnsOne() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "mr").then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleTitleMismatchReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "male", "queen").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatchReturnsZero() {
        given().when().get("/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "mrs").then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMismatchReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "female", "duke").then().assertThat().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneTitleMatchReturnsTwo() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "none", "prof").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "alive2").then().statusCode(lessThan(300));
        given().when().get("/api/title/{sex}/{title}", "other", "dr").then().assertThat().body(equalTo("-1"));
    }
}