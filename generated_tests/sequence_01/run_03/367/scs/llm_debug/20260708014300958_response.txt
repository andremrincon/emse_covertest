package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testTwo() {
        given()
            .when()
                .get("/api/text2txt/two/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForAndFour() {
        given()
            .when()
                .get("/api/text2txt/for/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));

        given()
            .when()
                .get("/api/text2txt/four/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testAnd() {
        given()
            .when()
                .get("/api/text2txt/and/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testAre() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/x")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}