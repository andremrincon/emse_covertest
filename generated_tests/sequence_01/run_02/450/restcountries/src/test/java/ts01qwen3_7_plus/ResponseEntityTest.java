package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnNameNotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityOnCapitalNotFound() {
        given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404);
    }
}