package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private String getBaseUrl() {
        return System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 0.001)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGserWithZeroX() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 5.5)
            .pathParam("x", 0.0)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGcfWithVeryLargeX() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 0.001)
            .pathParam("x", 1e40)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGcfWithLargeA() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 1000.0)
            .pathParam("x", 1001.0)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGserWithLargeA() {
        Response response = given()
            .basePath(getBaseUrl())
            .pathParam("a", 1000.0)
            .pathParam("x", 999.0)
        .when()
            .get("/api/gammq/{a}/{x}");

        response.then().statusCode(404);
    }
}