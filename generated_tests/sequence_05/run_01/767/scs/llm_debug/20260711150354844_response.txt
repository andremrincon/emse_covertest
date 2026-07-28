package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null
            ? System.getenv("BASE_URL")
            : "http://localhost:8080";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleProf() {
        given()
            .when()
                .get("/api/title/male/prof")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .when()
                .get("/api/title/male/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleProf() {
        given()
            .when()
                .get("/api/title/female/prof")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .when()
                .get("/api/title/female/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleProf() {
        given()
            .when()
                .get("/api/title/none/prof")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidSex() {
        given()
            .when()
                .get("/api/title/neuter/dr")
            .then()
                .statusCode(200);
    }
}