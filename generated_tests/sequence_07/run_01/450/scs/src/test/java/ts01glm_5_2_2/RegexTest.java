package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectUrlPatternMatchStatusCode() throws Exception {
        String txt = "http://a/b";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectDatePatternMatchStatusCode() throws Exception {
        String txt = "mon01jan";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFpePatternMatchStatusCode() throws Exception {
        String txt = "12.34e+56";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNonePatternMatchStatusCode() throws Exception {
        String txt = "hello";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectUrlPatternMatchBody() throws Exception {
        String txt = "http://a/b";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testSubjectNonePatternMatchBody() throws Exception {
        String txt = "hello";
        given()
            .urlEncodingEnabled(false)
        .when()
            .get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"))
        .then()
            .body(containsString("none"));
    }
}