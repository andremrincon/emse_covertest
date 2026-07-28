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
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturnsOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsReturnsZero() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNoneWithDrReturnsTwo() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testMaleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        resp.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testMixedCaseInputsAreHandledCaseInsensitively() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "MaLe", "Dr");
        resp.then().body(equalTo("1"));
    }
}