package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String apiBase = Optional.ofNullable(System.getProperty("API_BASE"))
                .orElseGet(() -> Optional.ofNullable(System.getenv("API_BASE"))
                        .orElse("http://localhost:8080"));
        RestAssured.baseURI = apiBase;
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get("/api/expint/1/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 2);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 4, 5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateNonTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        act.then().statusCode(200);
    }
}