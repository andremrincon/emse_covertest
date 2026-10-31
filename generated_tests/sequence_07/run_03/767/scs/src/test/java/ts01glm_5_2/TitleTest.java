package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithMatchingTitleMr() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithNonMatchingTitle() {
        given()
            .when()
                .get("/api/title/male/smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithMatchingTitleMrs() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithNonMatchingTitle() {
        given()
            .when()
                .get("/api/title/female/smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithMatchingTitleDr() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithNonMatchingTitle() {
        given()
            .when()
                .get("/api/title/none/smith")
            .then()
                .statusCode(200);
    }
}