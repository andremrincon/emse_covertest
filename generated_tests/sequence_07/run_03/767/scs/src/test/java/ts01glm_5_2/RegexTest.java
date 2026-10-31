package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
            .get("/api/pat/{txt}", "ftp://b/c")
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
            .get("/api/pat/{txt}", "12.34e-56")
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
}