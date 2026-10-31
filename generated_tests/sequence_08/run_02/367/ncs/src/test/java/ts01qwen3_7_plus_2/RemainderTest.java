package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Test;

public class RemainderTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        int a = 17;
        int b = 5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        int a = 17;
        int b = -5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        int a = -17;
        int b = 5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        int a = -17;
        int b = -5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .when()
                .get(baseUrl + "/api/remainder/{a}/{b}")
                .then()
                .statusCode(200);
    }
}