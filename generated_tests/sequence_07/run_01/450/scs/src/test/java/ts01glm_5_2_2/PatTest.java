package ts01glm_5_2_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessOrEqualTwo() {
        given()
            .pathParam("txt", "ABC")
            .pathParam("pat", "AB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given()
            .pathParam("txt", "ABCXYZ")
            .pathParam("pat", "ABC")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundAdjacent() {
        given()
            .pathParam("txt", "ABCCBA")
            .pathParam("pat", "ABC")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundNonAdjacent() {
        given()
            .pathParam("txt", "ABABCDCBA")
            .pathParam("pat", "ABC")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundAdjacent() {
        given()
            .pathParam("txt", "CBAABC")
            .pathParam("pat", "ABC")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given()
            .pathParam("txt", "CBAXYZ")
            .pathParam("pat", "ABC")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}