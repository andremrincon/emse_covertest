package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSubjectWithUrlPattern() {
        given()
            .baseUri(BASE_URL)
            .pathParam("txt", "http://abc/def")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectWithDatePattern() {
        given()
            .baseUri(BASE_URL)
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithFpePattern() {
        given()
            .baseUri(BASE_URL)
            .pathParam("txt", "00.00e+00")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithNoMatch() {
        given()
            .baseUri(BASE_URL)
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}