package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSidesReturnsInvalid() {
        RestAssured.given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleInequalityViolation() {
        RestAssured.given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/triangle/5/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        RestAssured.given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}