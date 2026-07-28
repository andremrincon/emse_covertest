package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RemainderTest {

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        int a = 17;
        int b = 5;
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        int a = 17;
        int b = -9;
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        int a = -17;
        int b = 5;
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        int a = -17;
        int b = -5;
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        Response response = given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}");

        response.then().statusCode(200);
    }
}