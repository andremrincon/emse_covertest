package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testCalcPlusOperation() {
        given().when().get(BASE_URL + "/api/calc/plus/10.0/5.0").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/calc/plus/10.0/5.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPiConstant() {
        given().when().get(BASE_URL + "/api/calc/pi/0.0/0.0").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/calc/pi/0.0/0.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrtOperation() {
        given().when().get(BASE_URL + "/api/calc/sqrt/16.0/0.0").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/calc/sqrt/16.0/0.0");

        response.then().statusCode(200);
    }
}