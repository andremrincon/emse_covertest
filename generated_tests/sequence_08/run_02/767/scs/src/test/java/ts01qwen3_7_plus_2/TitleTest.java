package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleMr() {
        given()
                .when()
                .get("/api/title/male/mr")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleSmith() {
        given()
                .when()
                .get("/api/title/male/smith")
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
    public void testFemaleSmith() {
        given()
                .when()
                .get("/api/title/female/smith")
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
    public void testNoneSmith() {
        given()
                .when()
                .get("/api/title/none/smith")
                .then()
                .statusCode(200);
    }
}