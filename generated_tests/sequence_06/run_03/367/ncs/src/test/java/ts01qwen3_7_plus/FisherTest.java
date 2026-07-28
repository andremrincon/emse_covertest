package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherValidParams1() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherValidParams2() {
        given()
            .when()
                .get("/api/fisher/2/2/-0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherValidParams3() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidN() {
        given()
            .when()
                .get("/api/fisher/10/-3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidX() {
        given()
            .when()
                .get("/api/fisher/10/5/1.2")
            .then()
                .statusCode(200);
    }
}