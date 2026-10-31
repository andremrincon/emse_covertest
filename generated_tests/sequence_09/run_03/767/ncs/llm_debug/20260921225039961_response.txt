package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
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
    public void testTriangle_Equilateral_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/3/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangle_NonPositiveSide_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/1/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangle_DegenerateInequality_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/2/2");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangle_Isosceles_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangle_ScaleneRight_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}