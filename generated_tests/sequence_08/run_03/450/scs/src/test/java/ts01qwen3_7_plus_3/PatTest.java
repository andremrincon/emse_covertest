package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPat1() {
        given()
            .when()
                .get("/api/pat/ABABCABAB/ABAB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat2() {
        given()
            .when()
                .get("/api/pat/banana/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat3() {
        given()
            .when()
                .get("/api/pat/some-text-to-search/pattern")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat4() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat5() {
        given()
            .when()
                .get("/api/pat/abcxxcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat6() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat7() {
        given()
            .when()
                .get("/api/pat/cbaxxabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat8() {
        given()
            .when()
                .get("/api/pat/xabcx/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPat9() {
        given()
            .when()
                .get("/api/pat/xcbax/abc")
            .then()
                .statusCode(200);
    }
}