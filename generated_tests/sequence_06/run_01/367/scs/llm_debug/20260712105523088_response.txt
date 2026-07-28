package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/invalid")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/invalid")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        given()
            .when()
            .get("/api/title/none/invalid")
            .then()
            .statusCode(200);
    }
}