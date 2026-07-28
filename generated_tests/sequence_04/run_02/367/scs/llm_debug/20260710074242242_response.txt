package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleMr() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/male/mr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleMrs() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/male/mrs")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMrs() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/female/mrs")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMr() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/female/mr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneDr() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/none/dr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMr() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/title/none/mr")
                .then()
                .statusCode(200);
    }
}