package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class PatTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "abc")
            .pathParam("pat", "ab")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "xabcx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "xabccba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "xcbax")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "xcbaabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        given()
            .baseUri(baseUrl)
            .pathParam("txt", "xabcxcba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}