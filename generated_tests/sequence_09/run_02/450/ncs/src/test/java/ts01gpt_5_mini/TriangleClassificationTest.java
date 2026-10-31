package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidWhenNonPositiveSidesResultsInOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/0/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonTriangleWhenOneSideGeqSumOthersReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/10/3/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/5/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsOkStatus() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/4/5/6");
        act.then().statusCode(200);
    }
}