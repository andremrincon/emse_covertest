package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TitleTest {

    @Test(timeout = 60000)
    public void testMaleProf() {
        given()
            .when()
                .get("http://localhost:8080/api/title/male/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleUnknown() {
        given()
            .when()
                .get("http://localhost:8080/api/title/male/unknown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleProf() {
        given()
            .when()
                .get("http://localhost:8080/api/title/female/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleUnknown() {
        given()
            .when()
                .get("http://localhost:8080/api/title/female/unknown")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneProf() {
        given()
            .when()
                .get("http://localhost:8080/api/title/none/prof")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneUnknown() {
        given()
            .when()
                .get("http://localhost:8080/api/title/none/unknown")
            .then()
                .statusCode(200);
    }
}