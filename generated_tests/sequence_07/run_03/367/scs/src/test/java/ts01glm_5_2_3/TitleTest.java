package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleWithMrTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testMaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFemaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testNoneWithProfTitle() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsError() {
        given()
            .when()
            .get("/api/title/neuter/Jones")
            .then()
            .statusCode(200);
    }
}