package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqual2() {
        given()
            .pathParam("txt", "abc")
            .pathParam("pat", "ab")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .pathParam("txt", "xyzxyz")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .pathParam("txt", "abcdef")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfter() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLater() {
        given()
            .pathParam("txt", "abcxcba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .pathParam("txt", "cbaxyz")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatImmediatelyAfter() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatLater() {
        given()
            .pathParam("txt", "cbaxabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundInnerLoopDoesNotExecute() {
        given()
            .pathParam("txt", "abc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}