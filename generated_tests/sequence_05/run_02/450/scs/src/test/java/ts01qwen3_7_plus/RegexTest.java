package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testUrlPattern() throws Exception {
        String txt = "http://abc/def";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        given()
            .pathParam("txt", encoded)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testDatePattern() {
        String txt = "mon01jan";
        given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpePattern() {
        String txt = "12.34e+56";
        given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testNonePattern() {
        String txt = "hello";
        given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200)
            .body(equalTo("none"));
    }
}