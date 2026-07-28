package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        String txt = "http://abc/def";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        String txt = "mon01jan";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        String txt = "12.34e+56";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        String txt = "hello";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchWithFtp() {
        String txt = "ftp://xyz/abc";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatchWithFri() {
        String txt = "fri25dec";
        given()
            .when()
                .get("/api/pat/" + txt)
            .then()
                .statusCode(lessThan(300));
    }
}