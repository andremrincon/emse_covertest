package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i28() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i1True_i7() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_i2True_i3False() {
        given()
            .when()
                .get("/api/notypevar/0/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_allFalse_i3_sHello() {
        given()
            .when()
                .get("/api/notypevar/3/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_invalidInput_400() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i1False_i2True_i3True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200);
    }
}