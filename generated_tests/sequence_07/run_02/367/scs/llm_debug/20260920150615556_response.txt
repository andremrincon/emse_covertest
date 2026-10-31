package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
    }

    @Test(timeout = 60000)
    public void testMaleValidTitleProf() {
        given()
            .when()
                .get("/api/title/male/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .when()
                .get("/api/title/male/Smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitleProf() {
        given()
            .when()
                .get("/api/title/female/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .when()
                .get("/api/title/female/Smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitleProf() {
        given()
            .when()
                .get("/api/title/none/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSex() {
        given()
            .when()
                .get("/api/title/neuter/Jones")
            .then()
                .statusCode(200);
    }
}