package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
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
    public void testFemaleWithValidTitleMrs() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(lessThan(300));
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
    public void testMaleWithInvalidTitleReturnsError() {
        given()
            .when()
                .get("/api/title/male/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitleReturnsError() {
        given()
            .when()
                .get("/api/title/female/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSexWithAnyTitleReturnsError() {
        given()
            .when()
                .get("/api/title/neuter/dr")
            .then()
                .statusCode(200);
    }
}