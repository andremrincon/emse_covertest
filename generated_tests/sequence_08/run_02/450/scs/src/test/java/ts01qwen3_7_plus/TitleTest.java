package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleMatchingTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMatchingTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMatchingTitle() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleMismatchingTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mrs")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMismatchingTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mr")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMismatchingTitle() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "mr")
            .when()
            .get("/api/title/{sex}/{title}")
            .then()
            .statusCode(200);
    }
}