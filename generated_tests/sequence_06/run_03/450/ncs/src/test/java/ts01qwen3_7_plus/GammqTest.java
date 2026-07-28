package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGserWithPositiveXLessThanAPlusOne() {
        Response response = given()
                .pathParam("a", 5.5)
                .pathParam("x", 2.3)
                .when()
                .get("/api/gammq/{a}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfWithXGreaterOrEqualToAPlusOne() {
        Response response = given()
                .pathParam("a", 1.0)
                .pathParam("x", 5.0)
                .when()
                .get("/api/gammq/{a}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserWithXEqualToZero() {
        Response response = given()
                .pathParam("a", 5.5)
                .pathParam("x", 0.0)
                .when()
                .get("/api/gammq/{a}/{x}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidALessThanOrEqualToZero() {
        Response response = given()
                .pathParam("a", -1.0)
                .pathParam("x", 2.0)
                .when()
                .get("/api/gammq/{a}/{x}");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidXLessThanZero() {
        Response response = given()
                .pathParam("a", 5.5)
                .pathParam("x", -1.0)
                .when()
                .get("/api/gammq/{a}/{x}");

        response.then().statusCode(400);
    }
}