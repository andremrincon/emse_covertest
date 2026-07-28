package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSideTriangle_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle_returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}