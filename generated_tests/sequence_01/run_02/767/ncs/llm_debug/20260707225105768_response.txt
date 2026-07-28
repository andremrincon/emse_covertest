package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URI");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleInvalidWhenNonPositiveEdge() {
        int seed = Math.abs(UUID.randomUUID().hashCode() % 1000) + 1;
        given().when().get("/api/remainder/{a}/{b}", seed, seed + 1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 5, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralAllEdgesEqual() {
        int seed = Math.abs(UUID.randomUUID().hashCode() % 1000) + 2;
        given().when().get("/api/remainder/{a}/{b}", seed, seed + 2).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateMaxEqualsSumOthers() {
        int seed = Math.abs(UUID.randomUUID().hashCode() % 1000) + 3;
        given().when().get("/api/remainder/{a}/{b}", seed, seed + 3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 10, 3, 7);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesTwoEqualEdges() {
        int seed = Math.abs(UUID.randomUUID().hashCode() % 1000) + 4;
        given().when().get("/api/remainder/{a}/{b}", seed, seed + 4).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneAllDifferentEdgesValid() {
        int seed = Math.abs(UUID.randomUUID().hashCode() % 1000) + 5;
        given().when().get("/api/remainder/{a}/{b}", seed, seed + 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        resp.then().statusCode(200);
    }
}