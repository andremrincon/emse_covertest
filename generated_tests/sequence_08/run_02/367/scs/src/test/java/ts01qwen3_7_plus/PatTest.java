package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
        .when()
            .get(baseUrl + "/api/pat/abc/ab")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
        .when()
            .get(baseUrl + "/api/pat/ABABxBABx/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
        .when()
            .get(baseUrl + "/api/pat/BABAxAxxA/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevImmediately() {
        given()
        .when()
            .get(baseUrl + "/api/pat/ABABBABA/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevLater() {
        given()
        .when()
            .get(baseUrl + "/api/pat/ABABxxBABA/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatImmediately() {
        given()
        .when()
            .get(baseUrl + "/api/pat/BABAABAB/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatLater() {
        given()
        .when()
            .get(baseUrl + "/api/pat/BABAxxABAB/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatPartialThenPatRev() {
        given()
        .when()
            .get(baseUrl + "/api/pat/AxxBABA/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevPartialThenPat() {
        given()
        .when()
            .get(baseUrl + "/api/pat/BxxABAB/ABAB")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }
}