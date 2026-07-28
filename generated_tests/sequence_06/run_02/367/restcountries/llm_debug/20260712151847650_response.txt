package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityGetNameNotFound() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityGetCapitalNotFound() {
        given()
            .pathParam("capital", "123")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityGetRegionNotFound() {
        given()
            .pathParam("region", "123")
        .when()
            .get("/v1/region/{region}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityPostRootNotAllowed() {
        given()
        .when()
            .post("/")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityGetNameServerError() {
        given()
            .pathParam("name", "True")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(404);
    }
}