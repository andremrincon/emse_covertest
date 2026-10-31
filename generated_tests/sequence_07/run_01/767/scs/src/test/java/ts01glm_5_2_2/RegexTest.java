package ts01glm_5_2_2;

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
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
                .get("/api/pat/http%3A%2F%2Fa%2Fa")
            .then()
                .statusCode(400);
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
                .get("/api/pat/00.00e%2B00")
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
    public void testSubjectUrlMatchFtp() {
        given()
            .when()
                .get("/api/pat/ftp%3A%2F%2Fb%2Fc")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatchWedAug() {
        given()
            .when()
                .get("/api/pat/wed15aug")
            .then()
                .statusCode(lessThan(300));
    }
}