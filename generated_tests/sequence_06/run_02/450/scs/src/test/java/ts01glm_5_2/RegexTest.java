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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
                .get("/api/pat/http://a/b")
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
    public void testSubjectUrlMatchFtp() {
        given()
            .when()
                .get("/api/pat/ftp://x/y")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatchTueFeb() {
        given()
            .when()
                .get("/api/pat/tue99feb")
            .then()
                .statusCode(lessThan(300));
    }
}