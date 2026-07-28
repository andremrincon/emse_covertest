package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testLogOperation() {
        given().when().get("/api/calc/pi/1.0/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/log/10.0/1.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtOperation() {
        given().when().get("/api/calc/e/1.0/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/sqrt/16.0/1.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideOperation() {
        given().when().get("/api/calc/plus/1.0/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/divide/10.0/2.0");
        response.then().statusCode(200);
    }
}