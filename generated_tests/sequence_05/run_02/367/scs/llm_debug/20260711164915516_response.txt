package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleMr() {
        given()
        .when()
            .get("/api/title/male/mr")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
        .when()
            .get("/api/title/male/xyz")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleMrs() {
        given()
        .when()
            .get("/api/title/female/mrs")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
        .when()
            .get("/api/title/female/xyz")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleDr() {
        given()
        .when()
            .get("/api/title/none/dr")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnknownSexNeuter() {
        given()
        .when()
            .get("/api/title/neuter/dr")
        .then()
            .statusCode(200);
    }
}