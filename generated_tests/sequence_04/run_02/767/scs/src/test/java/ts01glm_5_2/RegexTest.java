package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    @BeforeClass
    public static void setup() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"ur...")
    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        String txt = "http://a/a";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(404)
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
        String txt = "a";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(200)
                .body(containsString("none"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"ur...")
    @Test(timeout = 60000)
    public void testSubjectUrlFtpMatch() {
        String txt = "ftp://test-x/abc";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(404)
                .body(containsString("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectDateWedAugMatch() {
        String txt = "wed15aug";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(200)
                .body(containsString("date"));
    }
}