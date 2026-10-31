package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/abc/a")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundButPatRevNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzabcxyz/abc")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevFoundImmediately() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzabccba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevFoundNotImmediately() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzabc123cba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundButPatNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzcbaxyz/abc")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatFoundImmediately() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzcbaabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatFoundNotImmediately() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/xyzcba123abc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }
}