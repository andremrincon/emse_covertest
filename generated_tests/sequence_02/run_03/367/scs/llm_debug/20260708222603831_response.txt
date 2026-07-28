package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TitleTest {

    private final String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testMaleProf() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/male/prof")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/male/invalid")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/female/prof")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/female/invalid")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/none/prof")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/title/none/invalid")
                .then()
                .statusCode(200);
    }
}