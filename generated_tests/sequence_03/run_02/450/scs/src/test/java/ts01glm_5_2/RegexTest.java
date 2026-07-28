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
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
                .get("/api/pat/http://abc/def")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        given()
            .when()
                .get("/api/pat/mon01jan")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        given()
            .when()
                .get("/api/pat/12.34e+56")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        given()
            .when()
                .get("/api/pat/hello")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchAltProtocol() {
        given()
            .when()
                .get("/api/pat/ftp://x-y_z/abc")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatchAltDayMonth() {
        given()
            .when()
                .get("/api/pat/fri99dec")
            .then()
                .statusCode(lessThan(300));
    }
}