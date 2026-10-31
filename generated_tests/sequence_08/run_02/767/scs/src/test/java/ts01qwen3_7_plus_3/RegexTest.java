package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RegexTest {

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

        given()
            .pathParam("txt", "mon12jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}