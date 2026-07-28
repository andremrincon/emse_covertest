package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositiveAPositiveB_iterations() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveApositiveB_noIteration() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 4, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveANegativeB() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 10, -3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeAPositiveB() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, 4);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeANegativeB() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, -3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidParameterBadRequest() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", "abc", 0);
        resp.then().statusCode(400);
    }
}