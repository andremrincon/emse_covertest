package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testMaleSexWithValidMaleTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleSexWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/Smith")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleSexWithValidFemaleTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleSexWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/Jones")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneSexWithValidNeutralTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnrecognizedSexReturnsDefaultResult() {
        given()
            .when()
            .get("/api/title/f/Li")
            .then()
            .statusCode(200);
    }
}