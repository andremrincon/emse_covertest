package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i2False_i3True() {
        given()
            .when()
            .get("/api/notypevar/28/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i1True_i2True_i3True() {
        given()
            .when()
            .get("/api/notypevar/7/i")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i2True_i3False() {
        given()
            .when()
            .get("/api/notypevar/3/i")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_allFalse() {
        given()
            .when()
            .get("/api/notypevar/0/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i2True() {
        given()
            .when()
            .get("/api/notypevar/28/i")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_invalidInputReturns400() {
        given()
            .when()
            .get("/api/notypevar/abc/a")
            .then()
            .statusCode(400);
    }
}