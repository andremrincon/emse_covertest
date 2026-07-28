package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThan3() {
        given()
            .when()
                .get("/api/pat/hello/ab")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/hello%20world/world")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReversePatFoundNoPat() {
        given()
            .when()
                .get("/api/pat/hello%20dlrow/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseFoundNotAdjacent() {
        given()
            .when()
                .get("/api/pat/world%20hello%20dlrow/world")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseFoundAdjacent() {
        given()
            .when()
                .get("/api/pat/worlddlrow/world")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatFoundNotAdjacent() {
        given()
            .when()
                .get("/api/pat/dlrow%20hello%20world/world")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatFoundAdjacent() {
        given()
            .when()
                .get("/api/pat/dlrowworld/world")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/hello/xyz")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}