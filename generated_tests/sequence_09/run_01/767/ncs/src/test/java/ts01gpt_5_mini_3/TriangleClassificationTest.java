package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideReturns200() {
        int aSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        int bSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        given().when().get("/api/remainder/{a}/{b}", aSetup, bSetup).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        int aSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        int bSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        given().when().get("/api/remainder/{a}/{b}", aSetup, bSetup).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 2).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        int aSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        int bSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        given().when().get("/api/remainder/{a}/{b}", aSetup, bSetup).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        int aSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        int bSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        given().when().get("/api/remainder/{a}/{b}", aSetup, bSetup).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        int aSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        int bSetup = ThreadLocalRandom.current().nextInt(1, 1000);
        given().when().get("/api/remainder/{a}/{b}", aSetup, bSetup).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}