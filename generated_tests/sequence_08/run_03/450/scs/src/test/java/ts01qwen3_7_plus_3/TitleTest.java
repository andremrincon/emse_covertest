package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .pathParam("sex", "male")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .pathParam("sex", "female")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .pathParam("sex", "none")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherSex() {
        given()
            .pathParam("sex", "other")
            .pathParam("title", "prof")
        .when()
            .get("/api/title/{sex}/{title}")
        .then()
            .statusCode(200);
    }
}