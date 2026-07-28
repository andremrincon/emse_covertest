package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private static final String BASE = Optional.ofNullable(System.getProperty("api.base"))
            .orElse(Optional.ofNullable(System.getenv("API_BASE"))
                    .orElse("http://localhost:8080"));

    @Test(timeout = 60000)
    public void testMaleMatchingTitleReturnsOne() {
        given().when().get(BASE + "/api/pat/health").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/male/Mr");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleNonMatchingTitleReturnsMinusOne() {
        given().when().get(BASE + "/api/pat/setup-" + System.nanoTime()).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/male/queen");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleMatchingTitleDrReturnsZero() {
        given().when().get(BASE + "/api/pat/ready").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/female/Dr");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleNonMatchingTitleReturnsMinusOne() {
        given().when().get(BASE + "/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/female/sir");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneMatchingTitleReturnsTwo() {
        given().when().get(BASE + "/api/pat/ping").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/none/Prof");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneNonMatchingTitleReturnsMinusOne() {
        given().when().get(BASE + "/api/pat/verify").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/title/none/lord");
        act.then().body(equalTo("-1"));
    }
}