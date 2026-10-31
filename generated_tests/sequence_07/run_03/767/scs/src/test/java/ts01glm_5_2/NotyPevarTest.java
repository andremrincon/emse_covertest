package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1False_i2True_i3False() {
        given()
            .when()
                .get("/api/notypevar/0/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0False_i1False_i2False_i3False() {
        given()
            .when()
                .get("/api/notypevar/5/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/abc/a")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubject_integerOverflowReturns400() {
        given()
            .when()
                .get("/api/notypevar/2147483648/a")
            .then()
                .statusCode(400);
    }
}