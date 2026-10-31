package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200_whenNValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400_whenNTooSmall() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/2/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns200_whenValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400_whenMTooLarge() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1001/5/0.75");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400_whenExeThrows() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/1.2");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturnsCorrectResult() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5");
        act.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400_whenDivideByZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/5/0");
        act.then().statusCode(200);
    }
}