package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidSidesNonPositiveReturnsZero() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        when().get("/api/triangle/0/1/1").then().body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testEquilateralReturnsThree() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        when().get("/api/triangle/5/5/5").then().body("resultAsInt", equalTo(3));
    }

    @Test(timeout = 60000)
    public void testDegenerateMaxGreaterOrEqualSumReturnsZero() {
        given().when().get("/api/expint/1/2.5").then().statusCode(lessThan(300));
        when().get("/api/triangle/10/3/3").then().body("resultAsInt", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testIsoscelesReturnsTwo() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        when().get("/api/triangle/5/5/3").then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testScaleneReturnsOne() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        when().get("/api/triangle/3/4/5").then().body("resultAsInt", equalTo(1));
    }
}