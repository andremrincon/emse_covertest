package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleSir() {
        given()
            .when()
            .get("/api/title/male/sir")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMs() {
        given()
            .when()
            .get("/api/title/female/ms")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneRev() {
        given()
            .when()
            .get("/api/title/none/rev")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(200);
    }
}