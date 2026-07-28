package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleWithAcceptedTitleReturnsOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "Male", "Mr");
        act.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithUnacceptedTitleReturnsMinusOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "male", "queen");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithAcceptedTitleReturnsZero() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "FEMALE", "Miss");
        act.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithUnacceptedTitleReturnsMinusOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "female", "lord");
        act.then().body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneWithAcceptedTitleReturnsTwo() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "Dr");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneWithUnacceptedTitleReturnsMinusOne() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/{sex}/{title}", "none", "visitor");
        act.then().body(equalTo("-1"));
    }
}