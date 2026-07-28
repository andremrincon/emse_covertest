package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidSides_nonPositive_executesClassificationAndReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral_executesClassificationAndReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/7/3").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleInequalityViolation_executesClassificationAndReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/11/4").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles_executesClassificationAndReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/13/6").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene_executesClassificationAndReturns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/remainder/19/8").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}