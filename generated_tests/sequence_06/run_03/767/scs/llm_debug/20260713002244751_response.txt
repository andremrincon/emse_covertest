package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
        }
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
                .get("/api/title/male/xyz")
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
                .get("/api/title/female/xyz")
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
                .get("/api/title/other/mr")
            .then()
                .statusCode(200);
    }
}