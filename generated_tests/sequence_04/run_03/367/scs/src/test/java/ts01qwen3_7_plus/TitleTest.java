package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleOther() {
        given()
            .when()
            .get("/api/title/male/other")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleOther() {
        given()
            .when()
            .get("/api/title/female/other")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneOther() {
        given()
            .when()
            .get("/api/title/none/other")
            .then()
            .statusCode(200);
    }
}