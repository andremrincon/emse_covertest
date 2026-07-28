package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        String txt = "http://a/a";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        String txt = "mon01jan";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        String txt = "12.34e+56";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        String txt = "hello";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlFtpProtocol() {
        String txt = "ftp://ab/cd";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateAnotherDayMonth() {
        String txt = "fri99dec";
        given()
            .when()
                .get("/api/pat/{txt}", txt)
            .then()
                .statusCode(200);
    }
}