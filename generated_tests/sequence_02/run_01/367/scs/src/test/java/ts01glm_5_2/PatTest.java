package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        String host = System.getenv("SERVER_HOST");
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getenv("SERVER_PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanOrEqualTwo() {
        given()
            .when()
                .get("/api/pat/abc/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseFound() {
        given()
            .when()
                .get("/api/pat/helloabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfterPalindrome() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundLater() {
        given()
            .when()
                .get("/api/pat/abcXYZcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatImmediatelyAfterPalindrome() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatFoundLater() {
        given()
            .when()
                .get("/api/pat/cbaXYZabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatFound() {
        given()
            .when()
                .get("/api/pat/cbaXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesButFullPatternDoesNot() {
        given()
            .when()
                .get("/api/pat/aXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharOfReverseMatchesButFullReverseDoesNot() {
        given()
            .when()
                .get("/api/pat/cXYZ/abc")
            .then()
                .statusCode(200);
    }
}