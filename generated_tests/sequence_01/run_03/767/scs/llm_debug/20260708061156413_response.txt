package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleMr() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleMs() {
        given()
            .when()
            .get("/api/title/male/ms")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMr() {
        given()
            .when()
            .get("/api/title/female/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMr() {
        given()
            .when()
            .get("/api/title/none/mr")
            .then()
            .statusCode(200);
    }
}