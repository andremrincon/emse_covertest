package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Test(timeout = 60000)
    public void testLogOperation() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");


        RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get("/api/calc/pi/1.0/1.0")
                .then()
                .statusCode(lessThan(300));


        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get("/api/calc/log/10.0/0.0");


        response.then().statusCode(200);
    }
}