package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i1False_i2False_i3True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1True_i2True_i3True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1False_i2True_i3False() {
        given()
            .when()
                .get("/api/notypevar/3/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1False_i2False_i3False() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_i1False_i2True_i3True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }
}