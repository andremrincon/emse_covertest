package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUri = System.getProperty("base.uri");
        if (baseUri == null) {
            baseUri = System.getenv("BASE_URL");
        }
        if (baseUri == null) {
            baseUri = System.getenv("BASE_URI");
        }
        if (baseUri == null) {
            baseUri = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFromV1Name() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageFromV1Name() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404)
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFromV1Capital() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageFromV1Capital() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404)
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusFromV2Currency() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageFromV2Currency() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404)
            .body("message", equalTo("Not Found"));
    }
}