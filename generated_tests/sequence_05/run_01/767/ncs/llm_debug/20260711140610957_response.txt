package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideTriggersInvalidPath() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/0/1/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeSidesTriggerInvalidPath() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/-1/-1/-1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/3/3/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxEqualsSum() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/2/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/5/5/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/3/4/5");
        act.then().statusCode(200);
    }
}