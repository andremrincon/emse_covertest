package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroSideA() {
        Response response = given()
                .when()
                .get("/api/triangle/0/1/1");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroSideB() {
        Response response = given()
                .when()
                .get("/api/triangle/1/0/1");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroSideC() {
        Response response = given()
                .when()
                .get("/api/triangle/1/1/0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateral() {
        Response response = given()
                .when()
                .get("/api/triangle/3/3/3");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInequalityMaxA() {
        Response response = given()
                .when()
                .get("/api/triangle/4/1/2");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInequalityMaxC() {
        Response response = given()
                .when()
                .get("/api/triangle/1/2/4");

        response.then().statusCode(200);
    }
}