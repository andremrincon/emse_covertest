package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RegexTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
                .get("/api/pat/{txt}", "http://a/a")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        given()
            .when()
                .get("/api/pat/{txt}", "mon01jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        given()
            .when()
                .get("/api/pat/{txt}", "1.2e+34")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        given()
            .when()
                .get("/api/pat/{txt}", "hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidRegex() {
        given()
            .when()
                .get("/api/pat/{txt}", "(abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateParseEndpoint() {
        given()
            .when()
                .get("/api/dateparse/{dayname}/{monthname}", "Wednesday", "August")
            .then()
                .statusCode(200);
    }
}