package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectWithUrlPattern() {
        given()
            .pathParam("txt", "http://abc/def")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectWithDatePattern() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithFpePattern() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithNoMatch() {
        given()
            .pathParam("txt", "nomatch")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}