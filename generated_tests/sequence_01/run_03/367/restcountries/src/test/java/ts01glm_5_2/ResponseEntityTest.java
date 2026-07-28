package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String basePath = System.getProperty("basePath", "/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
    }

    @Test(timeout = 60000)
    public void testGetMessageOnNameNotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404)
            .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusOnNameNotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageOnCapitalNotFound() {
        given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404)
            .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusOnCapitalNotFound() {
        given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageOnRegionNotFound() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404)
            .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusOnRegionNotFound() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }
}