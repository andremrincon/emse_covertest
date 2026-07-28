package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
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
    public void testScaleneTriangleReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/2/2/2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeReturns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityFailsReturns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/1/2/3").then().statusCode(200);
    }
}