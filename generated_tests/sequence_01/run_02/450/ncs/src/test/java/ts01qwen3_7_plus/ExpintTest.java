package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        Response response = given()
                .when()
                .get("/api/expint/-1/1.0");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintN0() {
        Response response = given()
                .when()
                .get("/api/expint/0/2.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintX0() {
        Response response = given()
                .when()
                .get("/api/expint/2/0.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        Response response = given()
                .when()
                .get("/api/expint/1/2.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1XLessThan1() {
        Response response = given()
                .when()
                .get("/api/expint/1/0.5");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNGreaterThan1XLessThan1() {
        Response response = given()
                .when()
                .get("/api/expint/2/0.5");

        response.then().statusCode(200);
    }
}