package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectMaleTrue() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMaleFalse() {
        given()
            .when()
                .get("/api/title/male/unknown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFemaleTrue() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFemaleFalse() {
        given()
            .when()
                .get("/api/title/female/unknown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneTrue() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneFalse() {
        given()
            .when()
                .get("/api/title/none/unknown")
            .then()
                .statusCode(200);
    }
}