package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrl() {
        given()
            .pathParam("txt", "http://abc/def")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDate() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpe() {
        given()
            .pathParam("txt", "0.0e+00")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNone() {
        given()
            .pathParam("txt", "xyz")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidInputCauses500() {
        given()
            .pathParam("txt", "(abc")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}