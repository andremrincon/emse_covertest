package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String host = System.getenv("TEST_HOST");
        if (host == null || host.isEmpty()) {
            host = "localhost";
        }
        String port = System.getenv("TEST_PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        RestAssured.baseURI = "http://" + host + ":" + port;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatch() {
        given()
            .when()
                .get("/api/pat/http://abc/def")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDateMatch() {
        given()
            .when()
                .get("/api/pat/mon01jan")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpeMatch() {
        given()
            .when()
                .get("/api/pat/12.34e+56")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatch() {
        given()
            .when()
                .get("/api/pat/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlMatchWithPatternEndpoint() {
        given()
            .when()
                .get("/api/pat/http://abc/def/ABAB")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectNoneMatchWithPatternEndpoint() {
        given()
            .when()
                .get("/api/pat/hello/pattern")
            .then()
                .statusCode(lessThan(300));
    }
}