package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Test(timeout = 60000)
    public void testSubjectMatchesUrlHttp() {
        given()
            .pathParam("txt", "http://a/b")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrlFtp() {
        given()
            .pathParam("txt", "ftp://a/b")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDate() {
        given()
            .pathParam("txt", "mon12jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpePositive() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpeNegative() {
        given()
            .pathParam("txt", "12.34e-56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNone() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}