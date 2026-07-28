package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        RestAssured.baseURI = env != null && !env.isEmpty() ? env : System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void nonPositiveSideShouldBeClassifiedAsInvalid() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        act.then().body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void degenerateTriangleShouldBeClassifiedAsInvalid() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 1, 2, 3);
        act.then().body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void equilateralTriangleShouldBeClassifiedAsThree() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        act.then().body("resultAsInt", equalTo(3));
    }

    @Test(timeout = 60000)
    public void isoscelesTriangleShouldBeClassifiedAsTwo() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        act.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void scaleneTriangleShouldBeClassifiedAsOne() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        act.then().body("resultAsInt", equalTo(1));
    }
}