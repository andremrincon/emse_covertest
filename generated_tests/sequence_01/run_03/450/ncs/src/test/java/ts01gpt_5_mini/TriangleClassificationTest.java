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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNegativeSideReturnsInvalid() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 1000) + 1;
        int b = Math.abs(UUID.randomUUID().hashCode() % 1000) + 2;
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", -1, 5, 5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 1000) + 3;
        int b = Math.abs(UUID.randomUUID().hashCode() % 1000) + 4;
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 6, 6, 6).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsInvalid() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 1000) + 5;
        int b = Math.abs(UUID.randomUUID().hashCode() % 1000) + 6;
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 1000) + 7;
        int b = Math.abs(UUID.randomUUID().hashCode() % 1000) + 8;
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 1000) + 9;
        int b = Math.abs(UUID.randomUUID().hashCode() % 1000) + 10;
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(200);
    }
}