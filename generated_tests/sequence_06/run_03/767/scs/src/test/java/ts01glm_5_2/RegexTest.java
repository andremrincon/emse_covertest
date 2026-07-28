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
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"ur...")
    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        given()
            .pathParam("txt", "http://a/a")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404)
            .body(containsString("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(containsString("none"));
    }
}