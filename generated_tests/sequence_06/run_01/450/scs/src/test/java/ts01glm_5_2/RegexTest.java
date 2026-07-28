package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .pathParam("txt", "http://a/a")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        given()
            .pathParam("txt", "1.0e+12")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() {
        given()
            .pathParam("txt", "abc")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointWithPatternSearch() {
        given()
            .pathParam("txt", "ABABCABAB")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}