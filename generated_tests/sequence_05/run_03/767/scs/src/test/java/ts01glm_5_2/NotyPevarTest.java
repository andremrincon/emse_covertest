package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectAllBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubjectI0TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubjectI1True() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectI0TrueI2False() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubjectI1TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }
}