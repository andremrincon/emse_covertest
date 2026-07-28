package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Before;
import org.junit.Test;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/http%3A%2F%2Fa%2Fa")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        given()
            .pathParam("txt", "1.2e-34")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}