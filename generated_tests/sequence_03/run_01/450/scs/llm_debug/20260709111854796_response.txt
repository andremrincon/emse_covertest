package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/male/prof"));
    }

    @Test(timeout = 60000)
    public void testMaleSmith() {
        given()
            .when()
            .get("/api/title/male/Smith")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/male/Smith"));
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/female/prof"));
    }

    @Test(timeout = 60000)
    public void testFemaleSmith() {
        given()
            .when()
            .get("/api/title/female/Smith")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/female/Smith"));
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/none/prof"));
    }

    @Test(timeout = 60000)
    public void testNoneSmith() {
        given()
            .when()
            .get("/api/title/none/Smith")
            .then()
            .statusCode(404)
            .body("status", equalTo(404))
            .body("error", equalTo("Not Found"))
            .body("path", equalTo("/api/api/title/none/Smith"));
    }
}