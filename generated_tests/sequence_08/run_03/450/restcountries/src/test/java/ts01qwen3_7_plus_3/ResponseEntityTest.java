package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetStatus() {
        given()
            .when()
                .get("/v1/name/123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given()
            .when()
                .get("/v2/currency/123")
            .then()
                .statusCode(404)
                .body("message", equalTo("Not Found"));
    }
}