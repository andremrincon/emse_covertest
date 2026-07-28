package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/hello/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatNotFound() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/hello/xyz")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/helloabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevPalindrome() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevSeparate() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/abcXXXcba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevFoundOnly() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/hellocba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatPalindrome() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatSeparate() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/cbaXXXabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatSingleArg() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/pat/hello")
        .then()
            .statusCode(lessThan(300));
    }
}