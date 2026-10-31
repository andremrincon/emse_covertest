package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitleMr() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "smith")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitleMrs() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "smith")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitleDr() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "smith")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(500)));
    }
}