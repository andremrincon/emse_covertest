package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/mrs")
            .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/mr")
            .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }

    @Test(timeout = 60000)
    public void testNoneWithProfTitle() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/none/mr")
            .then()
            .statusCode(200)
            .body(equalTo("-1"));
    }
}