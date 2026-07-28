package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleMr() {
        RestAssured.given()
                .when()
                .get("/api/title/male/mr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUnknown() {
        RestAssured.given()
                .when()
                .get("/api/title/male/unknown")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        RestAssured.given()
                .when()
                .get("/api/title/female/mrs")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleUnknown() {
        RestAssured.given()
                .when()
                .get("/api/title/female/unknown")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        RestAssured.given()
                .when()
                .get("/api/title/none/dr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneUnknown() {
        RestAssured.given()
                .when()
                .get("/api/title/none/unknown")
                .then()
                .statusCode(200);
    }
}