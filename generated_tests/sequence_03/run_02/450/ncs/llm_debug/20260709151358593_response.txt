package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

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
    public void testTriangle_withNonPositiveEdge_returns200() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 100) + 1;
        int b = Math.abs(ThreadLocalRandom.current().nextInt(1, 10));
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 5, 5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangle_equilateral_returns200() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 100) + 1;
        int b = Math.abs(ThreadLocalRandom.current().nextInt(1, 10));
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangle_violatesTriangleInequality_returns200() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 100) + 1;
        int b = Math.abs(ThreadLocalRandom.current().nextInt(1, 10));
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 1, 1);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangle_isosceles_returns200() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 100) + 1;
        int b = Math.abs(ThreadLocalRandom.current().nextInt(1, 10));
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 2, 2, 3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangle_scalene_returns200() {
        int a = Math.abs(UUID.randomUUID().hashCode() % 100) + 1;
        int b = Math.abs(ThreadLocalRandom.current().nextInt(1, 10));
        given().when().get("/api/remainder/{a}/{b}", a, b).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        assertEquals(200, resp.getStatusCode());
    }
}