package ts01qwen3_7_plus;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .pathParam("txt", "hello")
            .pathParam("pat", "he")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundAndPatRevCharFoundButNotFullPatRev() {
        given()
            .pathParam("txt", "abcxxcxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundFirstThenPatLater() {
        given()
            .pathParam("txt", "xxcbaxxabcxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundFirstThenPatImmediately() {
        given()
            .pathParam("txt", "xxcbaabcxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundThenPatRevImmediately() {
        given()
            .pathParam("txt", "xxabccbaxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundThenPatRevLater() {
        given()
            .pathParam("txt", "xxabcxxcbaxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundOnly() {
        given()
            .pathParam("txt", "xxcbaxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly() {
        given()
            .pathParam("txt", "xxabcxx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorPatRevFoundButStartsWithChars() {
        given()
            .pathParam("txt", "abxcbx")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}