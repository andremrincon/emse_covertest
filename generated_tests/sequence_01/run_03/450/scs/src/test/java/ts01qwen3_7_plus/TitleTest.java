package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testTitleMaleValid() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mr")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTitleMaleInvalid() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "mrs")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTitleFemaleValid() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mrs")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTitleFemaleInvalid() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mr")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTitleNoneValid() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "dr")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTitleNoneInvalid() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "mr")
        .when()
            .get("http://localhost:8080/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}