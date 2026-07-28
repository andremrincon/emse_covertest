package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/x")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeNotYou() {
        given()
            .when()
                .get("/api/text2txt/see/x/y")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtNotSeeNotBy() {
        given()
            .when()
                .get("/api/text2txt/x/y/z")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheNotWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/x")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByNotThe() {
        given()
            .when()
                .get("/api/text2txt/by/x/y")
            .then()
                .statusCode(200);
    }
}