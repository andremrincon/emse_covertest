package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectI0TrueI2FalseI3True() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectI1TrueI2FalseI3True() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectI2TrueI3False() {
        given()
            .when()
                .get("/api/notypevar/3/world")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectAllFalseBranches() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubjectI0TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectI1TrueI2True() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200);
    }
}