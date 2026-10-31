package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abcXXX/abc")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaXXX/abc")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevSeparated() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abcXXXcba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatSeparated() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaXXXabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPartialMatchThenPatRev() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abXcba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}