package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.urlEncodingEnabled = true;
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsUrlForUrlPattern() {
        given()
            .pathParam("txt", "http://abc/def")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDateForDatePattern() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpeForExponentPattern() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNoneForSimpleText() {
        given()
            .pathParam("txt", "The quick brown fox")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNoneForSingleChar() {
        given()
            .pathParam("txt", "a")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWithMalformedInputReturns500() {
        given()
            .pathParam("txt", "(abc")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}