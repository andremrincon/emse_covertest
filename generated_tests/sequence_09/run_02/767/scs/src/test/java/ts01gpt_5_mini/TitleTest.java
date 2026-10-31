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
    public static void setUp() {
        String env = System.getenv("BASE_URL");
        RestAssured.baseURI = System.getProperty("baseUrl", env == null ? "http://localhost:8080" : env);
    }

    @Test(timeout = 60000)
    public void maleWithMrReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "Mr");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void femaleWithMrsReturnsZero() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FeMaLe", "Mrs");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void noneWithDrReturnsTwo() {
        given().when().get("/api/pat/{txt}", "pattern").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "DR");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void unknownSexReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "other", "UnknownTitle");
        act.then().body(equalTo("-1"));
    }
}