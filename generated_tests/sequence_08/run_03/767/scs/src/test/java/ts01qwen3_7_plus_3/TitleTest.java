package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
    public void testInvalidSex() {
        given()
            .pathParam("sex", "neuter")
            .pathParam("title", "Jones")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "Jones")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "mr")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}