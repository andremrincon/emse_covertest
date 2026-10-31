package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        Response response = given()
                .when()
                .get("/api/bessj/-5/2.5");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        Response response = given()
                .when()
                .get("/api/bessj/3/0.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAndLessThan8() {
        Response response = given()
                .when()
                .get("/api/bessj/3/5.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAndGreaterEqual8() {
        Response response = given()
                .when()
                .get("/api/bessj/3/10.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessEqualN() {
        Response response = given()
                .when()
                .get("/api/bessj/10/5.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeAndAxGreaterEqual8() {
        Response response = given()
                .when()
                .get("/api/bessj/3/-10.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeAndNEven() {
        Response response = given()
                .when()
                .get("/api/bessj/4/-10.0");

        response.then().statusCode(200);
    }
}