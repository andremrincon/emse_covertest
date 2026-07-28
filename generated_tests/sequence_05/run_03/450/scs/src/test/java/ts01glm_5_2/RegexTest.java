package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .pathParam("txt", "http://a/a")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        given()
            .pathParam("txt", "12.34e+56")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFtpUrlMatch() {
        given()
            .pathParam("txt", "ftp://b/c")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateWedAugMatch() {
        given()
            .pathParam("txt", "wed15aug")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}