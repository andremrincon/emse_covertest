package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTriangleNonPositiveInputsLeadToClientHandledResponse() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 0, 1, 1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 3, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateCaseIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", -9, 4).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 2, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 5, 5, 3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneIsHandled() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        act.then().statusCode(200);
    }
}