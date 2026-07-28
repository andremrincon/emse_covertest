package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatNoMatch() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abcde/xyz")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abcde/abc")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abcXXcba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotAdjacent() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaXXabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/cbaXX/abc")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}