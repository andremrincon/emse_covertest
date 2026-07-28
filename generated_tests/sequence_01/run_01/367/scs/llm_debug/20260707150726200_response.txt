package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "invalid")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}