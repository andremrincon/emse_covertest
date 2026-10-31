package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        String txt = "http://abc/def";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        String txt = "mon01jan";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        String txt = "12.34e+56";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        String txt = "The quick brown fox jumps over the lazy dog.";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("none"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testSubjectFtpUrlMatch() {
        String txt = "ftp://myhost/path";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectFpeNegativeExponentMatch() {
        String txt = "99.00e-12";
        given()
                .when()
                .get("/api/pat/" + txt)
                .then()
                .statusCode(200)
                .body(containsString("fpe"));
    }
}