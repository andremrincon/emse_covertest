package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFisher_validParameters_returns200() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisher_mExceedsLimit_returns400() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidXValue_returns400() {
        given()
                .when()
                .get("/api/fisher/10/5/1.2")
                .then()
                .statusCode(200);
    }
}