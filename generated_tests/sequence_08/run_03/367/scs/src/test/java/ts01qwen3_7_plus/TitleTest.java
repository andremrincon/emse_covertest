package ts01qwen3_7_plus;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

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
                .get("/api/title/male/smith")
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
                .get("/api/title/female/smith")
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
                .get("/api/title/none/smith")
            .then()
                .statusCode(200);
    }
}