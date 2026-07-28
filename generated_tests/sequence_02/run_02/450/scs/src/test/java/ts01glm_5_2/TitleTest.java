package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
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
                .get("/api/title/male/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
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
                .get("/api/title/female/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnknownSex() {
        given()
            .when()
                .get("/api/title/neuter/dr")
            .then()
                .statusCode(200);
    }
}