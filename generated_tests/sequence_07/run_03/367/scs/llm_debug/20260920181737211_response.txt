package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .when()
                .get("/api/title/male/Smith")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .when()
                .get("/api/title/female/Jones")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeuterWithAnyTitle() {
        given()
            .when()
                .get("/api/title/neuter/Jones")
            .then()
                .statusCode(200);
    }
}