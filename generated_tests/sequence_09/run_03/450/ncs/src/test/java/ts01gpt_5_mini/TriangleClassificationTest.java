package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidEdge_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonTriangle_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 10, 3, 2);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles_returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        act.then().statusCode(200);
    }
}