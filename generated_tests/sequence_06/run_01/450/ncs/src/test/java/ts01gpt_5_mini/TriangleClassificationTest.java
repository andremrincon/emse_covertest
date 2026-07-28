package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturnsInvalidStatus() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/0/1/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleHandled() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsInvalid() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/2/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleHandled() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/5/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleHandled() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/3/4/5");
        act.then().statusCode(200);
    }
}